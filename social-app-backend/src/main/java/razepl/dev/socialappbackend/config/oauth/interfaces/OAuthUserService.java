package razepl.dev.socialappbackend.config.oauth.interfaces;

import org.springframework.security.oauth2.core.user.OAuth2User;
import razepl.dev.socialappbackend.entities.user.User;

/**
 * This interface represents a service for managing OAuth users.
 */
public interface OAuthUserService {
    /**
     * Retrieves the OAuth user based on the registration ID and the OAuth2User.
     *
     * @param registrationId The registration ID associated with the OAuth provider.
     * @param oauthUser      The OAuth2User object representing the authenticated user.
     * @param <T>            The type of the OAuth2User.
     * @return An instance of IOAuthUser representing the retrieved OAuth user.
     */
    <T extends OAuth2User> OAuthUser getOAuthUser(String registrationId, T oauthUser);

    /**
     * Updates an existing user with the details from the OAuth user.
     *
     * @param user       The existing user to update.
     * @param oidcUser   The OAuth user containing the updated details.
     */
    void updateExistingUser(User user, OAuthUser oidcUser);

    /**
     * Registers an OAuth user.
     *
     * @param oidcUser The OAuth user to register.
     */
    void registerOAuthUser(OAuthUser oidcUser);
}

