package razepl.dev.socialappbackend.auth.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    @Operation(
            summary = "Post endpoint for registering user",
            description = "Backend receives RegisterRequest instance, register users into database and sends auth " +
                    "and refresh jwt tokens back with proper status.",
            responses = {
                    @ApiResponse(
                            description = "Success, returns auth and refresh token",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid request data",
                            responseCode = "403"
                    ),
                    @ApiResponse(
                            description = "User already registered",
                            responseCode = "422"
                    )
            }

    )
    AuthResponse registerUser(RegisterRequest registerRequest);

    /**
     * Logs a user in with the provided login request data.
     *
     * @param loginRequest The login request data.
     * @return A ResponseEntity containing an {@link AuthResponse} object with authentication and refresh tokens.
     */
    @Operation(
            summary = "Post endpoint for logging user in",
            description = "Backend receives username and password and sends proper data",
            responses = {
                    @ApiResponse(
                            description = "Success, returns auth and refresh token",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "User does not exist / Invalid request data",
                            responseCode = "403"
                    )
            }
    )
    AuthResponse loginUser(LoginRequest loginRequest);

    /**
     * Refreshes a user's authentication token using their refresh token.
     *
     * @param request  The HTTP servlet request containing the refresh token in the Authorization header.
     * @param response The HTTP servlet response to add the new authentication and refresh tokens to.
     * @return A ResponseEntity containing an {@link AuthResponse} object with the new authentication and refresh tokens.
     */
    @Operation(
            summary = "Post endpoint for refreshing users token",
            description = "Backend receives refresh token and returns new auth and refresh token",
            responses = {
                    @ApiResponse(
                            description = "Success, returns auth and refresh token",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid refresh token / Refresh token expired",
                            responseCode = "403"
                    )
            }
    )
    AuthResponse refreshUserToken(HttpServletRequest request, HttpServletResponse response);

    /**
     * Authenticates a user with the given token request.
     *
     * @param request the token request
     * @return a ResponseEntity with a TokenResponse as the response body
     */
    @Operation(
            summary = "Post endpoint for authenticating user",
            description = "Backend receives auth and refresh tokens and sens if user is authenticated",
            responses = {
                    @ApiResponse(
                            description = "Is authenticated",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Is not",
                            responseCode = "403"
                    )
            }
    )
    TokenResponse authenticateUser(TokenRequest request);
}
