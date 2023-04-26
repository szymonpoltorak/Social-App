package razepl.dev.socialappbackend.auth;

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
import razepl.dev.socialappbackend.exceptions.validators.NullChecker;

import static razepl.dev.socialappbackend.auth.constants.AuthMappings.*;

/**
 * Class to control auth endpoints.
 * It implements {@link AuthInterface}.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = AUTH_MAPPING)
public class AuthController implements AuthInterface {
    private final AuthServiceInterface authService;

    @Override
    @PostMapping(value = REGISTER_MAPPING)
    public final ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        NullChecker.throwAppropriateException(registerRequest);

        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @Override
    @PostMapping(value = LOGIN_MAPPING)
    public final ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        NullChecker.throwAppropriateException(loginRequest);

        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @Override
    @PostMapping(value = REFRESH_MAPPING)
    public final ResponseEntity<AuthResponse> refreshUserToken(HttpServletRequest request, HttpServletResponse response) {
        NullChecker.throwAppropriateException(request, response);

        return ResponseEntity.ok(authService.refreshToken(request, response));
    }

    @Override
    @PostMapping(value = AUTHENTICATE_MAPPING)
    public final ResponseEntity<TokenResponse> authenticateUser(@RequestBody TokenRequest request) {
        NullChecker.throwAppropriateException(request);

        return ResponseEntity.ok(authService.validateUsersTokens(request));
    }
}
