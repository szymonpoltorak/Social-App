package razepl.dev.socialappbackend.exceptions;

import jakarta.validation.ValidationException;

public class PasswordValidationException extends ValidationException {
    public PasswordValidationException(String message) {
        super(message);
    }
}
