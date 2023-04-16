package razepl.dev.socialappbackend.exceptions;

/**
 * Exception indicating that auth manager bean could not be instantiated.
 */
public class AuthManagerInstanceException extends InstantiationException {
    public AuthManagerInstanceException(String message) {
        super(message);
    }
}
