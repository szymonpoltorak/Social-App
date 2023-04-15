package razepl.dev.socialappbackend.config.interfaces;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtServiceInterface {
    String getUsernameFromToken(String token);

    <T> T getClaimFromToken(String token, Function<Claims, T> claimsHandler);

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> additionalClaims, UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
