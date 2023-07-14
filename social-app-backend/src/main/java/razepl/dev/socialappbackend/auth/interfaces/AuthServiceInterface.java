package razepl.dev.socialappbackend.auth.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import razepl.dev.socialappbackend.auth.apicalls.*;

/**
 * This interface provides methods for user authentication and authorization.
 */
public interface AuthServiceInterface {
    /**
     * Registers a user with the provided registration request data.
     *
     * @param userRequest The registration request data.
     * @return An AuthResponse object with authentication and refresh tokens.
     */
    AuthResponse register(RegisterRequest userRequest);

    /**
     * Logs a user in with the provided login request data.
     *
     * @param loginRequest The login request data.
     * @return An AuthResponse object with authentication and refresh tokens.
     */
    AuthResponse login(LoginRequest loginRequest);

    /**
     * Refreshes a user's authentication token using their refresh token.
     *
     * @param request  The HTTP servlet request containing the refresh token in the Authorization header.
     * @param response The HTTP servlet response to add the new authentication and refresh tokens to.
     * @return An AuthResponse object with the new authentication and refresh tokens.
     */
    AuthResponse refreshToken(HttpServletRequest request, HttpServletResponse response);

    /**
     * Validates the user's tokens using the given token request.
     *
     * @param request the token request containing the user's tokens
     * @return a token response containing information about the validity of the tokens
     */
    TokenResponse validateUsersTokens(TokenRequest request);

}

