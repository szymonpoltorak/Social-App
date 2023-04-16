package razepl.dev.socialappbackend.jwt.interfaces;

import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.user.User;

public interface TokenManager {
    String buildUsersAuthToken(User user);

    String buildRefreshToken(User user);

    AuthResponse buildTokensIntoResponse(String authToken, String refreshToken);

    void revokeUserTokens(User user);

    void saveUsersToken(String jwtToken, User user);
}
