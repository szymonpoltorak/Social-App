package razepl.dev.socialappbackend.config.handlers.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public interface IOAuthSuccessHandler extends AuthenticationSuccessHandler {
    void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException;
}
