package razepl.dev.socialappbackend.auth.interfaces;

import razepl.dev.socialappbackend.auth.responses.AuthResponse;

public interface AuthServiceInterface {
    AuthResponse register(RegisterUserRequest userRequest);
}
