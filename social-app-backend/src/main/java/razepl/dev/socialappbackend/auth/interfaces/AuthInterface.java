package razepl.dev.socialappbackend.auth.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.auth.apicalls.*;

/**
 * An interface that defines the methods for authentication services.
 */
public interface AuthInterface {
    /**
     * Registers a user with the provided registration request data.
     *
     * @param registerRequest The registration request data.
     * @return A ResponseEntity containing an {@link AuthResponse} object with authentication and refresh tokens.
     */
    ResponseEntity<AuthResponse> registerUser(RegisterRequest registerRequest);

    /**
     * Logs a user in with the provided login request data.
     *
     * @param loginRequest The login request data.
     * @return A ResponseEntity containing an {@link AuthResponse} object with authentication and refresh tokens.
     */
    ResponseEntity<AuthResponse> loginUser(LoginRequest loginRequest);

    /**
     * Refreshes a user's authentication token using their refresh token.
     *
     * @param request  The HTTP servlet request containing the refresh token in the Authorization header.
     * @param response The HTTP servlet response to add the new authentication and refresh tokens to.
     * @return A ResponseEntity containing an {@link AuthResponse} object with the new authentication and refresh tokens.
     */
    ResponseEntity<AuthResponse> refreshUserToken(HttpServletRequest request, HttpServletResponse response);

    ResponseEntity<TokenResponse> authenticateUser(TokenRequest request);
}
