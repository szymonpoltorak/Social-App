package razepl.dev.socialappbackend.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.auth.apicalls.LoginRequest;
import razepl.dev.socialappbackend.auth.apicalls.RegisterRequest;
import razepl.dev.socialappbackend.auth.interfaces.AuthInterface;
import razepl.dev.socialappbackend.auth.interfaces.AuthServiceInterface;

import static razepl.dev.socialappbackend.auth.constants.AuthMappings.*;
import static razepl.dev.socialappbackend.constants.GlobalConstants.FRONTEND_ADDRESS;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = AUTH_MAPPING)
@CrossOrigin(origins = FRONTEND_ADDRESS)
public class AuthController implements AuthInterface {
    private final AuthServiceInterface authService;

    @Override
    @PostMapping(value = REGISTER_MAPPING)
    public final ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @Override
    @PostMapping(value = LOGIN_MAPPING)
    public final ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @Override
    @PostMapping(value = REFRESH_MAPPING)
    public final ResponseEntity<AuthResponse> refreshUserToken(HttpServletRequest request, HttpServletResponse response) {
//        authService.refreshToken(request, response);
        return ResponseEntity.ok(authService.refreshToken(request, response));
    }
}
