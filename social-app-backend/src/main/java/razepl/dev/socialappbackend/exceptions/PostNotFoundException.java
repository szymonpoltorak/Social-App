package razepl.dev.socialappbackend.exceptions;

/**
 * Exception thrown when a post is not found.
 */
public class PostNotFoundException extends IllegalArgumentException {
    /**
     * Constructs a new PostNotFoundException with the specified error message.
     *
     * @param message the error message
     */
    public PostNotFoundException(String message) {
        super(message);
    }
}

