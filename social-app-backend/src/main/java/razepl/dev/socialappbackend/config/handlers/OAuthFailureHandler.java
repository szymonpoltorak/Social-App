package razepl.dev.socialappbackend.config.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import razepl.dev.socialappbackend.config.handlers.interfaces.IOAuthFailureHandler;

import java.io.IOException;

@Slf4j
@Component
public class OAuthFailureHandler extends SimpleUrlAuthenticationSuccessHandler implements IOAuthFailureHandler {
    @Override
    public final void onAuthenticationFailure(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException exception) throws IOException, ServletException {
        String targetUrl = "http://localhost:4200/auth/login";

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
