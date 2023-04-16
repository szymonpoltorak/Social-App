package razepl.dev.socialappbackend.auth.interfaces;

import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;

public interface AuthServiceInterface {
    AuthResponse register(RegisterUserRequest userRequest);

    AuthResponse login(LoginUserRequest loginRequest);
}
