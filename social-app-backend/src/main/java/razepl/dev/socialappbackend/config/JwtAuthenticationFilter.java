package razepl.dev.socialappbackend.config;

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
import razepl.dev.socialappbackend.config.interfaces.JwtServiceInterface;

import java.io.IOException;

import static razepl.dev.socialappbackend.config.constants.Headers.*;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtServiceInterface jwtService;

    @Override
    protected final void doFilterInternal(@NonNull HttpServletRequest request,
                                          @NonNull HttpServletResponse response,
                                          @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtAuthToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);

            return;
        }
        String username = jwtService.getUsernameFromToken(token);

        setTokenForNotAuthenticatedUser(token, username, request);

        filterChain.doFilter(request, response);
    }

    private void setTokenForNotAuthenticatedUser(String token, String username, @NonNull HttpServletRequest request) {
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
    }

    private String getJwtAuthToken(@NonNull HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_HEADER);

        if (request.getServletPath().contains(AUTH_MAPPING) || authHeader == null || !authHeader.startsWith(TOKEN_HEADER)) {
            return null;
        }
        return authHeader.substring(TOKEN_START_INDEX);
    }
}
