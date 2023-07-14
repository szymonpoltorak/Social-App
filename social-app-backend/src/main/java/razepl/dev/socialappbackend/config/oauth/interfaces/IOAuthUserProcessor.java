package razepl.dev.socialappbackend.config.oauth.interfaces;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * This interface represents a processor for an OAuth user.
 *
 * @param <R> The type of the OAuth2UserRequest.
 * @param <U> The type of the OAuth2User.
 */
public interface IOAuthUserProcessor<R extends OAuth2UserRequest, U extends OAuth2User> {
    /**
     * Processes the OAuth user registration based on the user request and OAuth2User.
     *
     * @param userRequest The user request object containing the OAuth user details.
     * @param oAuth2User  The OAuth2User object representing the authenticated user.
     * @return An instance of IOAuthUser representing the processed OAuth user.
     */
    IOAuthUser processOAuthUserRegistration(R userRequest, U oAuth2User);
}
