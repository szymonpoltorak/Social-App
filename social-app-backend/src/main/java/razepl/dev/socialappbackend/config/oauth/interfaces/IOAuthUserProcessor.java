package razepl.dev.socialappbackend.config.oauth.interfaces;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface IOAuthUserProcessor <R extends OAuth2UserRequest, U extends OAuth2User> {
    IOAuthUser processOAuthUserRegistration(R userRequest, U oAuth2User);
}
