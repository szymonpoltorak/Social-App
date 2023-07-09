package razepl.dev.socialappbackend.config.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthFailureHandler;

import java.io.IOException;

@Slf4j
@Component
public class OAuthFailureHandler extends SimpleUrlAuthenticationSuccessHandler implements IOAuthFailureHandler {
    @Override
    public final void onAuthenticationFailure(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException exception) throws IOException, ServletException {
        log.error("LOL");
        log.error("Http request : {}", request);
        log.error("Authentication exception : {}", exception.getLocalizedMessage());
        String targetUrl = "http://localhost:4200/auth/login";

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
