package razepl.dev.socialappbackend.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import razepl.dev.socialappbackend.oauth.interfaces.IOAuthFailureHandler;

import java.io.IOException;

@Component
public class OAuthFailureHandler implements IOAuthFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // TODO add failure handling
    }
}
