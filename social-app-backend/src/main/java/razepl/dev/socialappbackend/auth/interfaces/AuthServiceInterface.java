package razepl.dev.socialappbackend.auth.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;

import java.io.IOException;

public interface AuthServiceInterface {
    AuthResponse register(RegisterUserRequest userRequest);

    AuthResponse login(LoginUserRequest loginRequest);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
