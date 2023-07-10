package razepl.dev.socialappbackend.config.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.config.oauth.constants.AuthProvider;
import razepl.dev.socialappbackend.config.oauth.data.GithubOAuthUser;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUser;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUserService;
import razepl.dev.socialappbackend.entities.user.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.ServiceUser;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthUserService implements IOAuthUserService {
    private final UserRepository userRepository;

    @Override
    public final IOAuthUser getOAuthUser(String registrationId, OAuth2User oAuth2User) {
        Map<String, IOAuthUser> oAuthUserMap = Map.of(
                AuthProvider.GITHUB, buildGithubOAuthUser(oAuth2User)
        );
        registrationId = registrationId.toUpperCase();

        if (!oAuthUserMap.containsKey(registrationId)) {
            throw new IllegalStateException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
        return oAuthUserMap.get(registrationId);
    }

    @Override
    public final ServiceUser updateExistingUser(User user, IOAuthUser oAuthUser) {
        user.setName(oAuthUser.getName());
        user.setEmail(oAuthUser.getUsername());

        return userRepository.save(user);
    }

    @Override
    public final ServiceUser registerOAuthUser(IOAuthUser oAuthUser) {
        User user = User
                .builder()
                .name(oAuthUser.getName())
                .surname(oAuthUser.getFamilyName())
                .email(oAuthUser.getUsername())
                .dateOfBirth(oAuthUser.getBirthdate())
                .password(oAuthUser.getPassword())
                .role(Role.USER)
                .build();
        log.error("User : {}", user);
        return userRepository.save(user);
    }

    private IOAuthUser buildGithubOAuthUser(OAuth2AuthenticatedPrincipal oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String[] value = attributes.get("name").toString().split(" ");

        String name = value[0];
        String familyName = value[1];

        log.info("Full name : {}", Arrays.toString(value));
        log.error("Name : {}", name);
        log.error("FamilyName : {}", familyName);

        return GithubOAuthUser
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
}
