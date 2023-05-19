package razepl.dev.socialappbackend.exceptions;

/**
 * This class represents an exception that is thrown when a null argument is passed to a method or constructor.
 * It is a subclass of IllegalArgumentException.
 */
public class NullArgumentException extends IllegalArgumentException {
    /**
     * Constructs a new NullArgumentException with the specified detail message.
     *
     * @param message The detail message.
     */
    public NullArgumentException(String message) {
        super(message);
    }
}
