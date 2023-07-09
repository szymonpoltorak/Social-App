package razepl.dev.socialappbackend.config.jwt.interfaces;

import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.entities.user.User;

/**
 * An interface for managing authentication tokens for users.
 */
public interface TokenManager {
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
     * @param shouldIRevoke a flag indicating whether to revoke any existing tokens for the user
     * @return the constructed {@link AuthResponse} object
     */
    AuthResponse buildTokensIntoResponse(User user, boolean shouldIRevoke);

    /**
     * Revokes all tokens for the given user.
     *
     * @param user the user whose tokens should be revoked
     */
    void revokeUserTokens(User user);

    /**
     * Saves the given JWT token for the given user.
     *
     * @param jwtToken the JWT token to save
     * @param user     the user for whom to save the token
     */
    void saveUsersToken(String jwtToken, User user);
}
