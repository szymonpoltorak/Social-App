package razepl.dev.socialappbackend.config.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.config.oauth.constants.AuthProvider;
import razepl.dev.socialappbackend.config.oauth.data.OAuthUserImpl;
import razepl.dev.socialappbackend.config.oauth.interfaces.OAuthUser;
import razepl.dev.socialappbackend.config.oauth.interfaces.OAuthUserService;
import razepl.dev.socialappbackend.entities.user.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static razepl.dev.socialappbackend.config.oauth.constants.ProvidersAttributes.GITHUB_FULL_NAME;
import static razepl.dev.socialappbackend.config.oauth.constants.ProvidersAttributes.GITHUB_LOCATION;
import static razepl.dev.socialappbackend.config.oauth.constants.ProvidersAttributes.GITHUB_LOGIN;
import static razepl.dev.socialappbackend.config.oauth.constants.ProvidersAttributes.GITHUB_TOKEN;
import static razepl.dev.socialappbackend.config.oauth.constants.ProvidersAttributes.GOOGLE_FAMILY_NAME;
import static razepl.dev.socialappbackend.config.oauth.constants.ProvidersAttributes.GOOGLE_LOGIN;
import static razepl.dev.socialappbackend.config.oauth.constants.ProvidersAttributes.GOOGLE_NAME;
import static razepl.dev.socialappbackend.config.oauth.constants.ProvidersAttributes.GOOGLE_TOKEN;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthUserServiceImpl implements OAuthUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public final <T extends OAuth2User> OAuthUser getOAuthUser(String registrationId, T oauthUser) {
        Set<String> providers = Set.of(AuthProvider.GOOGLE, AuthProvider.GITHUB);

        registrationId = registrationId.toUpperCase();

        if (!providers.contains(registrationId)) {
            throw new IllegalStateException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
        return returnProperProviderObject(registrationId, oauthUser);
    }

    @Override
    public final void updateExistingUser(User user, OAuthUser oidcUser) {
        user.setName(oidcUser.getName());
        user.setEmail(oidcUser.getUsername());

        userRepository.save(user);
    }

    @Override
    public final void registerOAuthUser(OAuthUser oAuthUser) {
        Optional<String> github = Optional.ofNullable(oAuthUser.getGithub());
        Optional<String> location = Optional.ofNullable(oAuthUser.getLocation());

        User user = User
                .builder()
                .name(oAuthUser.getName())
                .surname(oAuthUser.getFamilyName())
                .email(oAuthUser.getUsername())
                .location(location.orElse(""))
                .dateOfBirth(LocalDate.now())
                .github(github.orElse(""))
                .password(passwordEncoder.encode(oAuthUser.getPassword()))
                .role(Role.USER)
                .build();
        log.error(user.toString());

        userRepository.save(user);
    }

    private OAuthUser returnProperProviderObject(String provider, OAuth2AuthenticatedPrincipal oAuth2User) {
        return provider.equals(AuthProvider.GOOGLE) ? buildGoogleOidcUser((OidcUser) oAuth2User) : buildGithubOAuthUser(oAuth2User);
    }

    private OAuthUser buildGithubOAuthUser(OAuth2AuthenticatedPrincipal oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String[] value = attributes.get(GITHUB_FULL_NAME).toString().split(" ");
        String name = value[0];
        String familyName = value[1];
        String login = attributes.get(GITHUB_LOGIN).toString() + "@github.com";
        Optional<String> location = Optional.of(attributes.get(GITHUB_LOCATION).toString());

        log.info("Full name : {}", Arrays.toString(value));
        log.error("Name : {}", name);
        log.error("FamilyName : {}", familyName);

        return OAuthUserImpl
                .builder()
                .name(name)
                .github(login)
                .authorities(oAuth2User.getAuthorities())
                .attributes(attributes)
                .location(location.orElse(""))
                .username(login)
                .birthdate(LocalDate.now())
                .password(attributes.get(GITHUB_TOKEN).toString())
                .familyName(familyName)
                .build();
    }

    private OAuthUser buildGoogleOidcUser(OidcUser oidcUser) {
        Map<String, Object> attributes = oidcUser.getAttributes();
        Optional<LocalDate> birthDate = Optional.ofNullable(oidcUser.getBirthdate()).map(LocalDate::parse);
        log.error(attributes.toString());

        return OAuthUserImpl
                .builder()
                .name(attributes.get(GOOGLE_NAME).toString())
                .userInfo(oidcUser.getUserInfo())
                .claims(oidcUser.getClaims())
                .idToken(oidcUser.getIdToken())
                .authorities(oidcUser.getAuthorities())
                .password(attributes.get(GOOGLE_TOKEN).toString())
                .attributes(attributes)
                .username(attributes.get(GOOGLE_LOGIN).toString())
                .birthdate(birthDate.orElse(LocalDate.now()))
                .familyName(attributes.get(GOOGLE_FAMILY_NAME).toString())
                .build();
    }
}
