package razepl.dev.socialappbackend.config.interfaces;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import razepl.dev.socialappbackend.jwt.interfaces.Token;
import razepl.dev.socialappbackend.user.User;

import java.util.Map;
import java.util.function.Function;

public interface JwtServiceInterface {
    String getUsernameFromToken(String jwtToken);

    <T> T getClaimFromToken(String jwtToken, Function<Claims, T> claimsHandler);

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> additionalClaims, UserDetails userDetails);

    boolean isTokenValid(String jwtToken, UserDetails userDetails);

    Token buildToken(String jwtToken, User user);
}
