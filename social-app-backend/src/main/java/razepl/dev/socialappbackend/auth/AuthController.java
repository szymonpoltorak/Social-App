package razepl.dev.socialappbackend.auth;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import razepl.dev.socialappbackend.user.ServiceUser;
import razepl.dev.socialappbackend.user.UserRepository;

import java.util.stream.Collectors;

import static razepl.dev.socialappbackend.auth.constants.AuthMappings.AUTH_MAPPING;
import static razepl.dev.socialappbackend.auth.constants.AuthMappings.REGISTER_MAPPING;
import static razepl.dev.socialappbackend.constants.GlobalConstants.FRONTEND_ADDRESS;

@Slf4j
@RestController
@CrossOrigin(origins = FRONTEND_ADDRESS)
@RequestMapping(value = AUTH_MAPPING)
public class AuthController implements AuthInterface {
    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @PostMapping(value = REGISTER_MAPPING)
    public ResponseEntity<String> registerUser(@Valid @RequestBody ServiceUser user) {
        userRepository.save(user);

        return ResponseEntity.ok("User has been successfully registered!");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationExceptions(ConstraintViolationException exception) {
        String errorMessage = exception.getConstraintViolations().stream()
                .map(error -> String.format("%s : %s", error.getPropertyPath(), error.getMessage()))
                .collect(Collectors.joining("\n"));

        log.error(errorMessage);

        return ResponseEntity.badRequest().body(errorMessage);
    }
}
