package razepl.dev.socialappbackend.entities.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.config.interfaces.JwtServiceInterface;
import razepl.dev.socialappbackend.entities.jwt.interfaces.TokenManager;
import razepl.dev.socialappbackend.entities.jwt.interfaces.TokenRepository;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.exceptions.validators.ArgumentValidator;

import java.util.List;

/**
 * Service class to manage building, saving and revoking user tokens.
 * It implements {@link TokenManager}.
 */
@Service
@RequiredArgsConstructor
public class TokenManagerService implements TokenManager {
    private final TokenRepository tokenRepository;
    private final JwtServiceInterface jwtService;

    @Override
    public final void saveUsersToken(String jwtToken, User user) {
        tokenRepository.save(buildToken(jwtToken, user));
    }

    @Override
    public final AuthResponse buildTokensIntoResponse(String authToken, String refreshToken) {
        return buildResponse(authToken, refreshToken);
    }

    @Override
    public final AuthResponse buildTokensIntoResponse(User user, boolean shouldIRevoke) {
        ArgumentValidator.throwIfNull(user);

        String authToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        if (shouldIRevoke) {
            revokeUserTokens(user);
        }
        saveUsersToken(authToken, user);

        return buildResponse(authToken, refreshToken);
    }

    @Override
    public final void revokeUserTokens(User user) {
        ArgumentValidator.throwIfNull(user);

        List<JwtToken> userTokens = tokenRepository.findAllValidTokensByUserId(user.getUserId());

        if (userTokens.isEmpty()) {
            return;
        }

        userTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepository.saveAll(userTokens);
    }

    private AuthResponse buildResponse(String authToken, String refreshToken) {
        ArgumentValidator.throwIfNull(authToken, refreshToken);

        return AuthResponse.builder()
                .authToken(authToken)
                .refreshToken(refreshToken)
                .build();
    }

    private JwtToken buildToken(String jwtToken, User user) {
        ArgumentValidator.throwIfNull(jwtToken, user);

        return JwtToken.builder()
                .token(jwtToken)
                .tokenType(TokenType.JWT_BEARER_TOKEN)
                .user(user)
                .build();
    }
}
