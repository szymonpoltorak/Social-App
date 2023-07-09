package razepl.dev.socialappbackend.config.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.config.oauth.constants.AuthProvider;
import razepl.dev.socialappbackend.config.oauth.data.GithubOAuthUser;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUser;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUserService;
import razepl.dev.socialappbackend.entities.user.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.ServiceUser;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuthUserService implements IOAuthUserService {
    private final UserRepository userRepository;

    @Override
    public final IOAuthUser getOAuthUser(String registrationId, Map<String, Object> attributes) {
        Map<String, IOAuthUser> oAuthUserMap = Map.of(
                AuthProvider.GITHUB, new GithubOAuthUser(attributes)
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
                .surname(oAuthUser.getSurname())
                .email(oAuthUser.getUsername())
                .dateOfBirth(oAuthUser.getDateOfBirth())
                .role(Role.USER)
                .build();
        return userRepository.save(user);
    }
}
