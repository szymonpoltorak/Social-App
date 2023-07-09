package razepl.dev.socialappbackend.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import razepl.dev.socialappbackend.config.jwt.interfaces.JwtFilter;
import razepl.dev.socialappbackend.config.jwt.interfaces.JwtServiceInterface;
import razepl.dev.socialappbackend.entities.token.interfaces.TokenRepository;

import java.io.IOException;

/**
 * Class made to add Jwt to security filter chain to let Jwt tokens to authenticate user and his requests.
 * This class extends {@link OncePerRequestFilter}.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter implements JwtFilter {
    private final UserDetailsService userDetailsService;
    private final JwtServiceInterface jwtService;
    private final TokenRepository tokenRepository;

    @Override
    public final void doFilterInternal(@NonNull HttpServletRequest request,
                                       @NonNull HttpServletResponse response,
                                       @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = jwtService.getJwtToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);

            return;
        }
        String username = jwtService.getUsernameFromToken(token);

        setTokenForNotAuthenticatedUser(token, username, request);

        filterChain.doFilter(request, response);
    }

    private void setTokenForNotAuthenticatedUser(String jwtToken, String username, @NonNull HttpServletRequest request) {
        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        boolean isTokenValid = tokenRepository.findByToken(jwtToken)
                .map(token -> !token.isExpired() && !token.isRevoked()).orElse(false);

        if (jwtService.isTokenValid(jwtToken, userDetails) && isTokenValid) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
}
