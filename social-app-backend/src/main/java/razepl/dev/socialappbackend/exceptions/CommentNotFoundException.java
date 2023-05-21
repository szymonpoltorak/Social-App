package razepl.dev.socialappbackend.exceptions;

public class CommentNotFoundException extends IllegalArgumentException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
