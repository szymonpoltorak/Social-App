package razepl.dev.socialappbackend.auth.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.exceptions.validators.NullChecker;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.auth.jwt.interfaces.TokenManager;
import razepl.dev.socialappbackend.auth.jwt.interfaces.TokenRepository;
import razepl.dev.socialappbackend.config.interfaces.JwtServiceInterface;
import razepl.dev.socialappbackend.user.User;

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
        NullChecker.throwAppropriateException(user);

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
        NullChecker.throwAppropriateException(user);

        List<JwtToken> userTokens = tokenRepository.findAllByUser(user.getUserId());

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
        NullChecker.throwAppropriateException(authToken, refreshToken);

        return AuthResponse.builder()
                .authToken(authToken)
                .refreshToken(refreshToken)
                .build();
    }

    private JwtToken buildToken(String jwtToken, User user) {
        NullChecker.throwAppropriateException(jwtToken, user);

        return JwtToken.builder()
                .token(jwtToken)
                .tokenType(TokenType.JWT_BEARER_TOKEN)
                .user(user)
                .build();
    }
}
