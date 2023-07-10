package razepl.dev.socialappbackend.config.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import razepl.dev.socialappbackend.config.jwt.interfaces.JwtServiceInterface;
import razepl.dev.socialappbackend.config.handlers.interfaces.IOAuthSuccessHandler;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements IOAuthSuccessHandler {
    private final JwtServiceInterface jwtService;

    @Override
    public final void onAuthenticationSuccess(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Authentication authentication) throws IOException, ServletException {
        handle(request, response, authentication);

        super.clearAuthenticationAttributes(request);
    }

    @Override
    public final void handle(HttpServletRequest request,
                             HttpServletResponse response,
                             Authentication authentication) throws IOException {
        String targetUrl = "http://localhost:8080/login";

        log.error("Principal : {}", authentication.getPrincipal());
        log.error("Credentials : {}", authentication.getCredentials());

//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

//        String token = jwtService.generateToken(userDetails);
//        String refreshToken = jwtService.generateRefreshToken(userDetails);

//        response.addCookie(cookieUtils.buildAuthTokenCookie(token));
//        response.addCookie(cookieUtils.buildRefreshTokenCookie(refreshToken));
//        response.addCookie(cookieUtils.buildUserCookie((UserDetails) authentication.getPrincipal()));
//        response.addHeader("AuthToken", token);
//        response.addHeader("RefreshToken", refreshToken);

        response.sendRedirect(targetUrl);
    }
}
