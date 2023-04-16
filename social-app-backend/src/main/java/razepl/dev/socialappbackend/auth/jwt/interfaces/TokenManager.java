package razepl.dev.socialappbackend.auth.jwt.interfaces;

import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.user.User;

public interface TokenManager {
    AuthResponse buildTokensIntoResponse(String authToken, String refreshToken);

    AuthResponse buildTokensIntoResponse(User user, boolean shouldIRevoke);

    void revokeUserTokens(User user);

    void saveUsersToken(String jwtToken, User user);
}
