package razepl.dev.socialappbackend.config.oidc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.config.oauth.constants.AuthProvider;
import razepl.dev.socialappbackend.config.oidc.data.GithubOidcUser;
import razepl.dev.socialappbackend.config.oidc.data.GoogleOidcUser;
import razepl.dev.socialappbackend.config.oidc.interfaces.IOidcUser;
import razepl.dev.socialappbackend.config.oidc.interfaces.IOidcUserService;
import razepl.dev.socialappbackend.entities.user.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OidcUserService implements IOidcUserService {
    private final UserRepository userRepository;

    @Override
    public final IOidcUser getOidcUser(String registrationId, OidcUser oidcUser) {
        Map<String, IOidcUser> oidcUserMap = Map.of(
                AuthProvider.GOOGLE, buildGoogleOidcUser(oidcUser),
                AuthProvider.GITHUB, buildGithubOidcUser(oidcUser)
        );
        registrationId = registrationId.toUpperCase();

        if (!oidcUserMap.containsKey(registrationId)) {
            throw new IllegalStateException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
        return oidcUserMap.get(registrationId);
    }

    @Override
    public final User updateExisitingUser(User user, IOidcUser oidcUser) {
        user.setName(oidcUser.getName());
        user.setEmail(oidcUser.getUsername());

        return userRepository.save(user);
    }

    @Override
    public final User registerOidcUser(IOidcUser oidcUser) {
        User user = User
                .builder()
                .name(oidcUser.getName())
                .surname(oidcUser.getFamilyName())
                .email(oidcUser.getUsername())
                .dateOfBirth(LocalDate.now())
                .password("Abc1!l1.DKk")
                .role(Role.USER)
                .build();
        log.error(user.toString());
        return userRepository.save(user);
    }

    private GoogleOidcUser buildGoogleOidcUser(OidcUser oidcUser) {
        Map<String, Object> attributes = oidcUser.getAttributes();

        log.error(attributes.toString());

        return GoogleOidcUser
                .builder()
                .name(attributes.get("given_name").toString())
                .userInfo(oidcUser.getUserInfo())
                .claims(oidcUser.getClaims())
                .idToken(oidcUser.getIdToken())
                .authorities(oidcUser.getAuthorities())
                .attributes(oidcUser.getAttributes())
                .username(attributes.get("email").toString())
                .birthdate(oidcUser.getBirthdate())
                .familyName(attributes.get("family_name").toString())
                .build();
    }

    private GithubOidcUser buildGithubOidcUser(OidcUser oidcUser) {
        return GithubOidcUser
                .builder()
                .name(oidcUser.getName())
                .userInfo(oidcUser.getUserInfo())
                .claims(oidcUser.getClaims())
                .idToken(oidcUser.getIdToken())
                .authorities(oidcUser.getAuthorities())
                .attributes(oidcUser.getAttributes())
                .username(oidcUser.getPreferredUsername())
                .birthdate(oidcUser.getBirthdate())
                .familyName(oidcUser.getFamilyName())
                .build();
    }
}
