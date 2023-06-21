package razepl.dev.socialappbackend.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import razepl.dev.socialappbackend.oauth.interfaces.IOAuthSuccessHandler;

import java.io.IOException;

@Component
public class OAuthSuccessHandler implements IOAuthSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // TODO auth success
    }
}
