package razepl.dev.socialappbackend.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import razepl.dev.socialappbackend.auth.data.*;
import razepl.dev.socialappbackend.auth.interfaces.AuthController;
import razepl.dev.socialappbackend.auth.interfaces.AuthServiceInterface;

import static razepl.dev.socialappbackend.auth.constants.AuthMappings.AUTHENTICATE_MAPPING;
import static razepl.dev.socialappbackend.auth.constants.AuthMappings.AUTH_MAPPING;
import static razepl.dev.socialappbackend.auth.constants.AuthMappings.LOGIN_MAPPING;
import static razepl.dev.socialappbackend.auth.constants.AuthMappings.REFRESH_MAPPING;
import static razepl.dev.socialappbackend.auth.constants.AuthMappings.REGISTER_MAPPING;

/**
 * Class to control auth endpoints.
 * It implements {@link AuthController}.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = AUTH_MAPPING)
@Tag(name = "User authentication")
public class AuthControllerImpl implements AuthController {
    private final AuthServiceInterface authService;

    @Override
    @PostMapping(value = REGISTER_MAPPING)
    @ResponseStatus(value = HttpStatus.CREATED)
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
