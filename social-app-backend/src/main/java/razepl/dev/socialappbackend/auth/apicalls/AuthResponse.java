package razepl.dev.socialappbackend.auth.apicalls;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The AuthResponse class is a model class representing the response to an authentication request.
 * This class includes an authentication token and a refresh token.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String authToken;
    private String refreshToken;
}
