package razepl.dev.socialappbackend.auth.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.auth.apicalls.LoginRequest;
import razepl.dev.socialappbackend.auth.apicalls.RegisterRequest;

/**
 * An interface that defines the methods for authentication services.
 */
public interface AuthInterface {
    ResponseEntity<AuthResponse> registerUser(RegisterRequest registerRequest);

    ResponseEntity<AuthResponse> loginUser(LoginRequest loginRequest);
}
