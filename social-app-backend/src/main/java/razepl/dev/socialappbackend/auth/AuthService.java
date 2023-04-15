package razepl.dev.socialappbackend.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.auth.interfaces.AuthServiceInterface;
import razepl.dev.socialappbackend.auth.interfaces.RegisterUserRequest;
import razepl.dev.socialappbackend.auth.responses.AuthResponse;
import razepl.dev.socialappbackend.auth.responses.RegisterRequest;
import razepl.dev.socialappbackend.config.interfaces.JwtServiceInterface;
import razepl.dev.socialappbackend.exceptions.PasswordValidationException;
import razepl.dev.socialappbackend.user.Role;
import razepl.dev.socialappbackend.user.User;
import razepl.dev.socialappbackend.user.interfaces.UserRepository;

import static razepl.dev.socialappbackend.user.constants.UserValidation.PASSWORD_PATTERN;
import static razepl.dev.socialappbackend.user.constants.UserValidationMessages.PASSWORD_PATTERN_MESSAGE;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {
    private final UserRepository userRepository;
    private final JwtServiceInterface jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public final AuthResponse register(RegisterUserRequest userRequest) {
        String password = userRequest.getPassword();

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new PasswordValidationException(PASSWORD_PATTERN_MESSAGE);
        }
        @Valid User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .dateOfBirth(userRequest.getDateOfBirth())
                .surname(userRequest.getSurname())
                .role(Role.USER)
                .password(passwordEncoder.encode(password))
                .build();
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return new AuthResponse(jwtToken);
    }
}
