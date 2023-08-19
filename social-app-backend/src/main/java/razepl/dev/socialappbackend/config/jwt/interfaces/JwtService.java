package razepl.dev.socialappbackend.config.jwt.interfaces;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

/**
 * This interface defines the operations that can be performed by a JWT service.
 */
public interface JwtService {
    /**
     * Retrieves the username from a given JWT token.
     *
     * @param jwtToken the JWT token from which to retrieve the username
     * @return the username retrieved from the token
     */
    String getUsernameFromToken(String jwtToken);

    /**
     * Retrieves a specific claim from a given JWT token, as defined by a handler function.
     *
     * @param jwtToken      the JWT token from which to retrieve the claim
     * @param claimsHandler a function that takes a Claims object and returns a specific claim from it
     * @param <T>           the type of the claim to retrieve
     * @return the retrieved claim
     */
    <T> T getClaimFromToken(String jwtToken, Function<Claims, T> claimsHandler);

    /**
     * Generates a JWT token for a given user.
     *
     * @param userDetails the user for which to generate the token
     * @return the generated JWT token
     */
    String generateToken(UserDetails userDetails);

    /**
     * Generates a JWT token for a given user with additional claims and expiration time.
     *
     * @param additionalClaims additional claims to include in the JWT token
     * @param userDetails      the user for which to generate the token
     * @param expiration       the expiration time for the token in milliseconds
     * @return the generated JWT token
     */
    String generateToken(Map<String, Object> additionalClaims, UserDetails userDetails, long expiration);

    /**
     * Validates whether a given JWT token is valid for a given user.
     *
     * @param jwtToken    the JWT token to validate
     * @param userDetails the user for which to validate the token
     * @return true if the token is valid for the user, false otherwise
     */
    boolean isTokenValid(String jwtToken, UserDetails userDetails);

    /**
     * Retrieves the JWT token from the Authorization header of a given HTTP request.
     *
     * @param request the HTTP request from which to retrieve the JWT token
     * @return the JWT token retrieved from the request
     */
    String getJwtToken(HttpServletRequest request);

    /**
     * Generates a refresh token for a given user.
     *
     * @param userDetails the user for which to generate the refresh token
     * @return the generated refresh token
     */
    String generateRefreshToken(UserDetails userDetails);

    /**
     * Retrieves the JWT refresh token from the Authorization header of a given HTTP request.
     *
     * @param request the HTTP request from which to retrieve the JWT refresh token
     * @return the JWT refresh token retrieved from the request
     */
    String getJwtRefreshToken(HttpServletRequest request);
}
