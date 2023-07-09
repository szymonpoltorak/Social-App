package razepl.dev.socialappbackend.config.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.config.oauth.constants.AuthProvider;
import razepl.dev.socialappbackend.config.oauth.data.GithubOAuthUser;
import razepl.dev.socialappbackend.config.oauth.data.GoogleOAuthUser;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUserService;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUser;
import razepl.dev.socialappbackend.entities.user.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuthUserService implements IOAuthUserService {
    private final UserRepository userRepository;

    @Override
    public final IOAuthUser getOAuthUser(String registrationId, Map<String, Object> attributes) {
        Map<String, IOAuthUser> oAuthUserMap = Map.of(
                AuthProvider.GOOGLE, new GoogleOAuthUser(attributes),
                AuthProvider.GITHUB, new GithubOAuthUser(attributes)
        );
        registrationId = registrationId.toUpperCase();

        if (!oAuthUserMap.containsKey(registrationId)) {
            throw new IllegalStateException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
        return oAuthUserMap.get(registrationId);
    }

    @Override
    public final User updateExisitingUser(User user, IOAuthUser oAuthUser) {
        user.setName(oAuthUser.getName());
        user.setEmail(oAuthUser.getUsername());

        return userRepository.save(user);
    }

    @Override
    public final User registerOAuthUser(IOAuthUser oAuthUser) {
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
