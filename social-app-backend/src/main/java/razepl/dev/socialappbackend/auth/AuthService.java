package razepl.dev.socialappbackend.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.auth.apicalls.*;
import razepl.dev.socialappbackend.auth.interfaces.AuthServiceInterface;
import razepl.dev.socialappbackend.config.jwt.interfaces.JwtServiceInterface;
import razepl.dev.socialappbackend.config.jwt.interfaces.TokenManager;
import razepl.dev.socialappbackend.entities.user.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.exceptions.*;
import razepl.dev.socialappbackend.validators.ArgumentValidator;

import java.util.Optional;

import static razepl.dev.socialappbackend.entities.user.constants.UserValidation.PASSWORD_PATTERN;
import static razepl.dev.socialappbackend.entities.user.constants.UserValidationMessages.PASSWORD_PATTERN_MESSAGE;

/**
 * Class to manage logic for {@link AuthController}.
 * It implements {@link AuthServiceInterface}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;
    private final JwtServiceInterface jwtService;

    @Override
    public final AuthResponse register(RegisterRequest registerRequest) {
        ArgumentValidator.throwIfNull(registerRequest);

        log.info("Registering user with data: \n{}", registerRequest);

        String password = validateUserRegisterData(registerRequest);

        @Valid User user = User
                .builder()
                .name(registerRequest.name())
                .email(registerRequest.email())
                .dateOfBirth(registerRequest.dateOfBirth())
                .surname(registerRequest.surname())
                .role(Role.USER)
                .password(passwordEncoder.encode(password))
                .build();
        userRepository.save(user);

        log.info("Building token response for user : {}", user);

        return tokenManager.buildTokensIntoResponse(user, false);
    }

    @Override
    public final AuthResponse login(LoginRequest loginRequest) {
        ArgumentValidator.throwIfNull(loginRequest);

        log.info("Logging user with data: \n{}", loginRequest);

        String username = loginRequest.username();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, loginRequest.password())
        );

        User user = userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Such user does not exist!")
        );
        log.info("Building token response for user : {}", user);

        return tokenManager.buildTokensIntoResponse(user, true);
    }

    @Override
    public final AuthResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {
        ArgumentValidator.throwIfNull(request, response);

        String refreshToken = jwtService.getJwtRefreshToken(request);

        log.info("Refresh token : {}", refreshToken);

        User user = validateRefreshTokenData(refreshToken);
        String authToken = jwtService.generateToken(user);

        log.info("New auth token : {}\nFor user : {}", authToken, user);

        tokenManager.revokeUserTokens(user);

        tokenManager.saveUsersToken(authToken, user);

        return tokenManager.buildTokensIntoResponse(authToken, refreshToken);
    }

    @Override
    public final TokenResponse validateUsersTokens(TokenRequest request) {
        ArgumentValidator.throwIfNull(request);

        log.info("Authenticating user with data:\n{}", request);

        User user = userRepository.findUserByToken(request.authToken()).orElseThrow(TokensUserNotFoundException::new);

        boolean isAuthTokenValid = jwtService.isTokenValid(request.authToken(), user);

        log.info("Is token valid : {}\nFor user : {}", isAuthTokenValid, user);

        return TokenResponse
                .builder()
                .isAuthTokenValid(isAuthTokenValid)
                .build();
    }

    private String validateUserRegisterData(RegisterRequest registerRequest) {
        String password = registerRequest.password();

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            log.error("Password was not valid! Password: {}", password);

            throw new PasswordValidationException(PASSWORD_PATTERN_MESSAGE);
        }
        Optional<User> existingUser = userRepository.findByEmail(registerRequest.email());

        if (existingUser.isPresent()) {
            log.error("User already exists! Found user: {}", existingUser.get());

            throw new UserAlreadyExistsException("User already exists!");
        }
        return password;
    }

    private User validateRefreshTokenData(String refreshToken) {
        if (refreshToken == null) {
            throw new TokenDoesNotExistException("Token does not exist!");
        }
        String username = jwtService.getUsernameFromToken(refreshToken);

        if (username == null) {
            throw new UsernameNotFoundException("Such user does not exist!");
        }
        log.info("User of username : {}", username);

        User user = userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Such user does not exist!")
        );

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new InvalidTokenException("Token is not valid!");
        }
        return user;
    }
}
