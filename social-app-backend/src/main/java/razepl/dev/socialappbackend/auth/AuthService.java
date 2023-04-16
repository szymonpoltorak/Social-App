package razepl.dev.socialappbackend.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import razepl.dev.socialappbackend.config.interfaces.JwtServiceInterface;
import razepl.dev.socialappbackend.exceptions.InvalidTokenException;
import razepl.dev.socialappbackend.exceptions.PasswordValidationException;
import razepl.dev.socialappbackend.exceptions.TokenDoesNotExistException;
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
    private final JwtServiceInterface jwtService;

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

        return tokenManager.buildTokensIntoResponse(user, false);
    }

    @Override
    public AuthResponse login(LoginUserRequest loginRequest) {
        String username = loginRequest.getUsername();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, loginRequest.getPassword())
        );

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Such user does not exist!"));

        return tokenManager.buildTokensIntoResponse(user, true);
    }

    @Override
    public AuthResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = jwtService.getJwtRefreshToken(request);

        if (refreshToken == null) {
            throw new TokenDoesNotExistException("Token does not exist!");
        }
        String username = jwtService.getUsernameFromToken(refreshToken);

        if (username == null) {
            throw new UsernameNotFoundException("Such user does not exist!");
        }
        User user = userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Such user does not exist!"));


        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new InvalidTokenException("Token is not valid!");
        }

        String authToken = jwtService.generateToken(user);

        tokenManager.revokeUserTokens(user);

        tokenManager.saveUsersToken(authToken, user);

        return tokenManager.buildTokensIntoResponse(authToken, refreshToken);
    }
}
