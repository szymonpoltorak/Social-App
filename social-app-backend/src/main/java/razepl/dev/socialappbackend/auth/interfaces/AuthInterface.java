package razepl.dev.socialappbackend.auth.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.auth.apicalls.LoginRequest;
import razepl.dev.socialappbackend.auth.apicalls.RegisterRequest;

import java.io.IOException;

/**
 * An interface that defines the methods for authentication services.
 */
public interface AuthInterface {
    ResponseEntity<AuthResponse> registerUser(RegisterRequest registerRequest);

    ResponseEntity<AuthResponse> loginUser(LoginRequest loginRequest);

    void refreshUsersToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
