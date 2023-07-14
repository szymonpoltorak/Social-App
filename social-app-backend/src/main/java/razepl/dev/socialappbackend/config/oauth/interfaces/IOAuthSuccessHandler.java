package razepl.dev.socialappbackend.config.oauth.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * This interface extends the AuthenticationSuccessHandler interface and provides a method for handling OAuth success.
 */
public interface IOAuthSuccessHandler extends AuthenticationSuccessHandler {
    /**
     * Handles the OAuth success by processing the HTTP request, HTTP response, and authentication details.
     *
     * @param request        The HttpServletRequest object.
     * @param response       The HttpServletResponse object.
     * @param authentication The Authentication object representing the authenticated user.
     * @throws IOException If an I/O error occurs during the handling process.
     */
    void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException;
}

