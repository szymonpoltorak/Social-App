package razepl.dev.socialappbackend.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.auth.apicalls.*;
import razepl.dev.socialappbackend.auth.interfaces.AuthInterface;
import razepl.dev.socialappbackend.auth.interfaces.AuthServiceInterface;

import static razepl.dev.socialappbackend.auth.constants.AuthMappings.*;

/**
 * Class to control auth endpoints.
 * It implements {@link AuthInterface}.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = AUTH_MAPPING)
@Tag(name = "User authentication")
public class AuthController implements AuthInterface {
    private final AuthServiceInterface authService;

    @Override
    @PostMapping(value = REGISTER_MAPPING)
    public final AuthResponse registerUser(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @Override
    @PostMapping(value = LOGIN_MAPPING)
    public final AuthResponse loginUser(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Override
    @PostMapping(value = REFRESH_MAPPING)
    public final AuthResponse refreshUserToken(HttpServletRequest request, HttpServletResponse response) {
        return authService.refreshToken(request, response);
    }

    @Override
    @PostMapping(value = AUTHENTICATE_MAPPING)
    public final TokenResponse authenticateUser(@RequestBody TokenRequest request) {
        return authService.validateUsersTokens(request);
    }
}
