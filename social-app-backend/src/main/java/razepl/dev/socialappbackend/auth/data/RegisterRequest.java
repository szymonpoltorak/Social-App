package razepl.dev.socialappbackend.auth.data;

import lombok.Builder;

import java.time.LocalDate;

/**
 * The RegisterRequest class is a model class representing a user registration request.
 */
@Builder
public record RegisterRequest(String name, String surname, String email, String password, LocalDate dateOfBirth) {
}
