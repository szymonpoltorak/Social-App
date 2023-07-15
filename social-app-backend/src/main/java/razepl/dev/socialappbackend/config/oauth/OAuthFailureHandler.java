package razepl.dev.socialappbackend.config.oauth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import razepl.dev.socialappbackend.config.oauth.constants.RedirectUrls;

import java.io.IOException;

@Slf4j
@Component
public class OAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public final void onAuthenticationFailure(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException exception) throws IOException {
        getRedirectStrategy().sendRedirect(request, response, RedirectUrls.FAILURE_URL);
    }
}
