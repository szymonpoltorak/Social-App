package razepl.dev.socialappbackend.auth.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.auth.apicalls.TokenRequest;
import razepl.dev.socialappbackend.auth.apicalls.TokenResponse;

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
    AuthResponse register(RegisterUserRequest userRequest);

    /**
     * Logs a user in with the provided login request data.
     *
     * @param loginRequest The login request data.
     * @return An AuthResponse object with authentication and refresh tokens.
     */
    AuthResponse login(LoginUserRequest loginRequest);

    /**
     * Refreshes a user's authentication token using their refresh token.
     *
     * @param request  The HTTP servlet request containing the refresh token in the Authorization header.
     * @param response The HTTP servlet response to add the new authentication and refresh tokens to.
     * @return An AuthResponse object with the new authentication and refresh tokens.
     */
    AuthResponse refreshToken(HttpServletRequest request, HttpServletResponse response);

    TokenResponse validateUsersTokens(TokenRequest request);
}

