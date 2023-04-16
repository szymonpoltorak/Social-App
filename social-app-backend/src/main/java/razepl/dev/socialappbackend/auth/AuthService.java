package razepl.dev.socialappbackend.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.auth.interfaces.AuthServiceInterface;
import razepl.dev.socialappbackend.auth.interfaces.LoginUserRequest;
import razepl.dev.socialappbackend.auth.interfaces.RegisterUserRequest;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.config.interfaces.JwtServiceInterface;
import razepl.dev.socialappbackend.exceptions.PasswordValidationException;
import razepl.dev.socialappbackend.jwt.JwtToken;
import razepl.dev.socialappbackend.jwt.interfaces.TokenRepository;
import razepl.dev.socialappbackend.user.Role;
import razepl.dev.socialappbackend.user.User;
import razepl.dev.socialappbackend.user.interfaces.UserRepository;

import java.util.List;

import static razepl.dev.socialappbackend.user.constants.UserValidation.PASSWORD_PATTERN;
import static razepl.dev.socialappbackend.user.constants.UserValidationMessages.PASSWORD_PATTERN_MESSAGE;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {
    private final UserRepository userRepository;
    private final JwtServiceInterface jwtService;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

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

        revokeUserTokens(user);
        tokenRepository.save((JwtToken) jwtService.buildToken(jwtToken, user));

        return AuthResponse.builder()
                .authToken(jwtToken)
                .build();
    }

    @Override
    public AuthResponse login(LoginUserRequest loginRequest) {
        String username = loginRequest.getUsername();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, loginRequest.getPassword())
        );

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Such user does not exist!"));

        String jwtToken = jwtService.generateToken(user);

        revokeUserTokens(user);

        tokenRepository.save((JwtToken) jwtService.buildToken(jwtToken, user));

        return AuthResponse.builder()
                .authToken(jwtToken)
                .build();
    }

    private void revokeUserTokens(User user) {
        List<JwtToken> userTokens = tokenRepository.findAllByUser(user);

        if (userTokens.isEmpty()) {
            return;
        }

        userTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepository.saveAll(userTokens);
    }
}
