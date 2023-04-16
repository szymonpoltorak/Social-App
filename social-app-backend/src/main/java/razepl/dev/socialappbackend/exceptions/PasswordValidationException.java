package razepl.dev.socialappbackend.exceptions;

import jakarta.validation.ValidationException;

/**
 * Exception meaning that password provided by the user does not meet requirements.
 */
public class PasswordValidationException extends ValidationException {
    public PasswordValidationException(String message) {
        super(message);
    }
}
