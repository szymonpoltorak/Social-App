package razepl.dev.socialappbackend.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.auth.jwt.JwtToken;
import razepl.dev.socialappbackend.auth.jwt.interfaces.TokenRepository;

import static razepl.dev.socialappbackend.config.constants.Headers.*;

/**
 * Service class for logging user out.
 */
@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final TokenRepository tokenRepository;

    @Override
    public final void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader(AUTH_HEADER);

        if (authHeader == null || !authHeader.startsWith(TOKEN_HEADER)) {
            return;
        }
        String jwt = authHeader.substring(TOKEN_START_INDEX);
        JwtToken token = tokenRepository.findByToken(jwt).orElse(null);

        if (token == null) {
            return;
        }
        token.setExpired(true);
        token.setRevoked(true);
        tokenRepository.save(token);

        SecurityContextHolder.clearContext();
    }
}
