package razepl.dev.socialappbackend.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.auth.interfaces.AuthServiceInterface;
import razepl.dev.socialappbackend.auth.interfaces.LoginUserRequest;
import razepl.dev.socialappbackend.auth.interfaces.RegisterUserRequest;
import razepl.dev.socialappbackend.exceptions.PasswordValidationException;
import razepl.dev.socialappbackend.jwt.interfaces.TokenManager;
import razepl.dev.socialappbackend.user.Role;
import razepl.dev.socialappbackend.user.User;
import razepl.dev.socialappbackend.user.interfaces.UserRepository;

import static razepl.dev.socialappbackend.user.constants.UserValidation.PASSWORD_PATTERN;
import static razepl.dev.socialappbackend.user.constants.UserValidationMessages.PASSWORD_PATTERN_MESSAGE;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

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

        String authToken = tokenManager.buildUsersAuthToken(user);

        return tokenManager.buildTokensIntoResponse(authToken);
    }

    @Override
    public AuthResponse login(LoginUserRequest loginRequest) {
        String username = loginRequest.getUsername();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, loginRequest.getPassword())
        );

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Such user does not exist!"));

        String authToken = tokenManager.buildUsersAuthToken(user);

        return tokenManager.buildTokensIntoResponse(authToken);
    }
}
