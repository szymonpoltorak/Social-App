package razepl.dev.socialappbackend.exceptions;

/**
 * This class represents an exception that is thrown when a user already exists.
 * It is a subclass of IllegalStateException.
 */
public class UserAlreadyExistsException extends IllegalStateException {
    /**
     * Constructs a new UserAlreadyExistsException with the specified detail message.
     *
     * @param message The detail message.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

