package razepl.dev.socialappbackend.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.config.interfaces.JwtServiceInterface;
import razepl.dev.socialappbackend.jwt.interfaces.Token;
import razepl.dev.socialappbackend.jwt.interfaces.TokenManager;
import razepl.dev.socialappbackend.jwt.interfaces.TokenRepository;
import razepl.dev.socialappbackend.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenManagerService implements TokenManager {
    private final TokenRepository tokenRepository;
    private final JwtServiceInterface jwtService;

    @Override
    public void saveUsersToken(String jwtToken, User user) {
        tokenRepository.save((JwtToken) buildToken(jwtToken, user));
    }

    @Override
    public final String buildUsersAuthToken(User user) {
        String jwtToken = jwtService.generateToken(user);

        revokeUserTokens(user);

        tokenRepository.save((JwtToken) buildToken(jwtToken, user));

        return jwtToken;
    }

    @Override
    public final String buildRefreshToken(User user) {
        return jwtService.generateRefreshToken(user);
    }

    @Override
    public AuthResponse buildTokensIntoResponse(String authToken, String refreshToken) {
        return AuthResponse.builder()
                .authToken(authToken)
                .refreshToken(refreshToken)
                .build();
    }

    private Token buildToken(String jwtToken, User user) {
        return JwtToken.builder()
                .token(jwtToken)
                .tokenType(TokenType.JWT_BEARER_TOKEN)
                .user(user)
                .build();
    }

    public final void revokeUserTokens(User user) {
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
}
