package razepl.dev.socialappbackend.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import razepl.dev.socialappbackend.auth.interfaces.AuthInterface;
import razepl.dev.socialappbackend.auth.interfaces.AuthServiceInterface;
import razepl.dev.socialappbackend.auth.responses.AuthResponse;
import razepl.dev.socialappbackend.auth.responses.RegisterRequest;

import static razepl.dev.socialappbackend.auth.constants.AuthMappings.AUTH_MAPPING;
import static razepl.dev.socialappbackend.auth.constants.AuthMappings.REGISTER_MAPPING;
import static razepl.dev.socialappbackend.constants.GlobalConstants.FRONTEND_ADDRESS;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = AUTH_MAPPING)
@CrossOrigin(origins = FRONTEND_ADDRESS)
public class AuthController implements AuthInterface {
    private final AuthServiceInterface authServiceInterface;

    @Override
    @PostMapping(value = REGISTER_MAPPING)
    public final ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest userRequest) {
        return ResponseEntity.ok(authServiceInterface.register(userRequest));
    }
}
