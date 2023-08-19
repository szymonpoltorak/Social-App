package razepl.dev.socialappbackend.auth.data;

import lombok.Builder;

/**
 * The AuthResponse class is a model class representing the response to an authentication request.
 * This class includes an authentication token and a refresh token.
 */
@Builder
public record AuthResponse(String authToken, String refreshToken) {
}
