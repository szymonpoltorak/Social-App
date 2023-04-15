package razepl.dev.socialappbackend.auth.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.auth.responses.AuthResponse;
import razepl.dev.socialappbackend.auth.responses.RegisterRequest;

/**
 * An interface that defines the methods for authentication services.
 */
public interface AuthInterface {
    ResponseEntity<AuthResponse> registerUser(RegisterRequest user);
}
