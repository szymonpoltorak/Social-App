package razepl.dev.socialappbackend.auth;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import razepl.dev.socialappbackend.auth.interfaces.AuthInterface;
import razepl.dev.socialappbackend.user.User;
import razepl.dev.socialappbackend.user.interfaces.UserRepository;

import static razepl.dev.socialappbackend.auth.constants.AuthMappings.AUTH_MAPPING;
import static razepl.dev.socialappbackend.auth.constants.AuthMappings.REGISTER_MAPPING;
import static razepl.dev.socialappbackend.auth.constants.AuthMessages.ADDED_INFO;
import static razepl.dev.socialappbackend.auth.constants.AuthMessages.SUCCESSFUL_REGISTER;
import static razepl.dev.socialappbackend.constants.GlobalConstants.FRONTEND_ADDRESS;

@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin(origins = FRONTEND_ADDRESS)
@RequestMapping(value = AUTH_MAPPING)
public class AuthController implements AuthInterface {
    private final UserRepository userRepository;

    @Override
    @PostMapping(value = REGISTER_MAPPING)
    public final ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        userRepository.save(user);

        log.info(ADDED_INFO);

        return ResponseEntity.ok(SUCCESSFUL_REGISTER);
    }
}
