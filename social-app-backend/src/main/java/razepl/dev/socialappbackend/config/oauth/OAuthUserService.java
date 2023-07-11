package razepl.dev.socialappbackend.config.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.config.oauth.constants.AuthProvider;
import razepl.dev.socialappbackend.config.oauth.data.OAuthUser;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUser;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUserService;
import razepl.dev.socialappbackend.entities.user.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthUserService implements IOAuthUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public final <T extends OAuth2User> IOAuthUser getOAuthUser(String registrationId, T oauthUser) {
        Set<String> providers = Set.of(AuthProvider.GOOGLE, AuthProvider.GITHUB);

        registrationId = registrationId.toUpperCase();

        if (!providers.contains(registrationId)) {
            throw new IllegalStateException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
        return returnProperProviderObject(registrationId, oauthUser);
    }

    @Override
    public final void updateExistingUser(User user, IOAuthUser oidcUser) {
        user.setName(oidcUser.getName());
        user.setEmail(oidcUser.getUsername());

        userRepository.save(user);
    }

    @Override
    public final void registerOAuthUser(IOAuthUser oAuthUser) {
        User user = User
                .builder()
                .name(oAuthUser.getName())
                .surname(oAuthUser.getFamilyName())
                .email(oAuthUser.getUsername())
                .dateOfBirth(LocalDate.now())
                .password(passwordEncoder.encode(oAuthUser.getPassword()))
                .role(Role.USER)
                .build();
        log.error(user.toString());

        userRepository.save(user);
    }

    private IOAuthUser returnProperProviderObject(String provider, OAuth2AuthenticatedPrincipal oAuth2User) {
        return provider.equals(AuthProvider.GOOGLE) ? buildGoogleOidcUser((OidcUser) oAuth2User) : buildGithubOAuthUser(oAuth2User);
    }

    private IOAuthUser buildGithubOAuthUser(OAuth2AuthenticatedPrincipal oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String[] value = attributes.get("name").toString().split(" ");

        String name = value[0];
        String familyName = value[1];

        log.info("Full name : {}", Arrays.toString(value));
        log.error("Name : {}", name);
        log.error("FamilyName : {}", familyName);

        return OAuthUser
                .builder()
                .name(name)
                .authorities(oAuth2User.getAuthorities())
                .attributes(oAuth2User.getAttributes())
                .username(attributes.get("login").toString() + "@github.com")
                .birthdate(LocalDate.now())
                .password("Abc1!l1.DKk")
                .familyName(familyName)
                .build();
    }

    private IOAuthUser buildGoogleOidcUser(OidcUser oidcUser) {
        Map<String, Object> attributes = oidcUser.getAttributes();

        LocalDate birthdate = oidcUser.getBirthdate() == null ? LocalDate.now() : LocalDate.parse(oidcUser.getBirthdate());

        log.error(attributes.toString());

        return OAuthUser
                .builder()
                .name(attributes.get("given_name").toString())
                .userInfo(oidcUser.getUserInfo())
                .claims(oidcUser.getClaims())
                .idToken(oidcUser.getIdToken())
                .authorities(oidcUser.getAuthorities())
                .password("Abc1!l1.DKk")
                .attributes(oidcUser.getAttributes())
                .username(attributes.get("email").toString())
                .birthdate(birthdate)
                .familyName(attributes.get("family_name").toString())
                .build();
    }
}
