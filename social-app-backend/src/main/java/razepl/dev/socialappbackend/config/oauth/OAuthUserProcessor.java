package razepl.dev.socialappbackend.config.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUser;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUserProcessor;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUserService;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthUserProcessor <R extends OAuth2UserRequest, U extends OAuth2User> implements IOAuthUserProcessor<R, U> {
    private final IOAuthUserService oauthUserService;
    private final UserRepository userRepository;

    @Override
    public final IOAuthUser processOAuthUserRegistration(R userRequest, U oAuth2User) {
        IOAuthUser oauthUser = oauthUserService.getOAuthUser(
                userRequest.getClientRegistration().getRegistrationId(),
                oAuth2User
        );
        String username = oauthUser.getUsername();

        log.info("Username: {}", username);

        if (username == null || username.isEmpty()) {
            throw new OAuth2AuthenticationException("Username has not been found!");
        }
        Optional<User> authenticatedUser = userRepository.findByEmail(username);

        if (authenticatedUser.isPresent()) {
            oauthUserService.updateExistingUser(authenticatedUser.get(), oauthUser);

            return oauthUser;
        }
        oauthUserService.registerOAuthUser(oauthUser);

        return oauthUser;
    }
}
