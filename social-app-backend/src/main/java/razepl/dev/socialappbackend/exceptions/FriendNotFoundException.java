package razepl.dev.socialappbackend.exceptions;

/**
 * Exception thrown when a friend is not found.
 */
public class FriendNotFoundException extends IllegalArgumentException {
    /**
     * Constructs a new FriendNotFoundException with the specified error message.
     *
     * @param message the error message
     */
    public FriendNotFoundException(String message) {
        super(message);
    }
}
