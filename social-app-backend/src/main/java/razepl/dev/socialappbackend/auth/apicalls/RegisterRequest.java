package razepl.dev.socialappbackend.auth.apicalls;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import razepl.dev.socialappbackend.auth.interfaces.RegisterUserRequest;

import java.time.LocalDate;

/**
 * The RegisterRequest class is a model class representing a user registration request.
 * This class includes user information and implements the {@link RegisterUserRequest} interface.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements RegisterUserRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
}
