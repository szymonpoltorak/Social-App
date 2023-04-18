package razepl.dev.socialappbackend.auth.interfaces;

import razepl.dev.socialappbackend.user.interfaces.UserPropertyInterface;

import java.time.LocalDate;

/**
 * This interface represents a request to register a user.
 * It extends {@link UserPropertyInterface}.
 */
public interface RegisterUserRequest extends UserPropertyInterface {

    /**
     * Returns the date of birth of the user.
     *
     * @return The date of birth of the user.
     */
    LocalDate getDateOfBirth();
}

