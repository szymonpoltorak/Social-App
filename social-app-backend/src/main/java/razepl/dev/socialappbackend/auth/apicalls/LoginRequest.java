package razepl.dev.socialappbackend.auth.apicalls;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import razepl.dev.socialappbackend.auth.interfaces.LoginUserRequest;

/**
 * The LoginRequest class is a model class representing a login request.
 * This class includes login credentials and implements the {@link LoginUserRequest} interface.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest implements LoginUserRequest {
    private String username;
    private String password;
}
