package razepl.dev.socialappbackend.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import razepl.dev.socialappbackend.user.ServiceUser;
import razepl.dev.socialappbackend.user.UserRepository;

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
    public ResponseEntity<String> registerUser(@RequestBody ServiceUser user) {
        userRepository.save(user);

        return ResponseEntity.ok("User has been successfully registered!");
    }
}
