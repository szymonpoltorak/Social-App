package razepl.dev.socialappbackend.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.auth.apicalls.*;
import razepl.dev.socialappbackend.auth.interfaces.AuthInterface;
import razepl.dev.socialappbackend.auth.interfaces.AuthServiceInterface;
import razepl.dev.socialappbackend.exceptions.validators.ArgumentValidator;

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
    public final ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        ArgumentValidator.throwIfNull(registerRequest);

        log.info("Registering user with data: \n{}", registerRequest);

        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @Override
    @PostMapping(value = LOGIN_MAPPING)
    public final ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        ArgumentValidator.throwIfNull(loginRequest);

        log.info("Logging user with data: \n{}", loginRequest);

        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @Override
    @PostMapping(value = REFRESH_MAPPING)
    public final ResponseEntity<AuthResponse> refreshUserToken(HttpServletRequest request, HttpServletResponse response) {
        ArgumentValidator.throwIfNull(request, response);

        log.info("Refreshing users token.");

        return ResponseEntity.ok(authService.refreshToken(request, response));
    }

    @Override
    @PostMapping(value = AUTHENTICATE_MAPPING)
    public final ResponseEntity<TokenResponse> authenticateUser(@RequestBody TokenRequest request) {
        ArgumentValidator.throwIfNull(request);

        log.info("Authenticating user with data:\n{}", request);

        return ResponseEntity.ok(authService.validateUsersTokens(request));
    }
}
