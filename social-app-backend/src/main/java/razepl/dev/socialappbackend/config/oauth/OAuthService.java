package razepl.dev.socialappbackend.config.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.config.oauth.data.OAuthUserPrincipal;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthService;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUserService;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUser;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService extends DefaultOAuth2UserService implements IOAuthService {
    private final IOAuthUserService oauthUserService;
    private final UserRepository userRepository;

    @Override
    public final OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.error("UserRequest: {}", userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("OAuth2User: {}", oAuth2User.getAttributes());

        return processOAuthUserAuthRequest(userRequest, oAuth2User);
    }

    private OAuth2User processOAuthUserAuthRequest(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        IOAuthUser oAuthUser = oauthUserService.getOAuthUser(
                userRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes()
        );
        String username = oAuthUser.getUsername();

        log.info("Username: {}", username);

        if (username == null || username.isEmpty()) {
            throw new OAuth2AuthenticationException("Username has not been found!");
        }
        Optional<User> authenticatedUser = userRepository.findByEmail(username);

        if (authenticatedUser.isPresent()) {
            User user = oauthUserService.updateExisitingUser(authenticatedUser.get(), oAuthUser);

            return OAuthUserPrincipal.create(user, oAuthUser.attributes());
        }
        User user = oauthUserService.registerOAuthUser(oAuthUser);

        return OAuthUserPrincipal.create(user, oAuthUser.attributes());
    }
}
