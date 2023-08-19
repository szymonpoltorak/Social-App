package razepl.dev.socialappbackend.config.jwt.interfaces;

import razepl.dev.socialappbackend.auth.data.AuthResponse;
import razepl.dev.socialappbackend.entities.user.User;

/**
 * An interface for managing authentication tokens for users.
 */
public interface TokenManagerService {
    /**
     * Builds an {@link AuthResponse} object with the given authentication and refresh tokens.
     *
     * @param authToken    the authentication token to include in the response
     * @param refreshToken the refresh token to include in the response
     * @return the constructed {@link AuthResponse} object
     */
    AuthResponse buildTokensIntoResponse(String authToken, String refreshToken);

    /**
     * Builds an {@link AuthResponse} object with authentication and refresh tokens generated from the given user.
     *
     * @param user          the user for which to generate tokens
     * @param shouldBeRevoked a flag indicating whether to revoke any existing tokens for the user
     * @return the constructed {@link AuthResponse} object
     */
    AuthResponse buildTokensIntoResponse(User user, boolean shouldBeRevoked);

    /**
     * Revokes all tokens for the given user.
     *
     * @param user the user whose tokens should be revoked
     */
    void revokeUserTokens(User user);

    /**
     * This method revokes all tokens associated with a given username.
     *
     * @param username The username for which to revoke tokens.
     */
    void revokeUserTokens(String username);

    /**
     * Saves the given JWT token for the given user.
     *
     * @param jwtToken the JWT token to save
     * @param user     the user for whom to save the token
     */
    void saveUsersToken(String jwtToken, User user);

    /**
     * This method saves a JWT token associated with a given username.
     *
     * @param jwtToken  The JWT token to save.
     * @param username  The username associated with the token.
     */
    void saveUsersToken(String jwtToken, String username);
}
