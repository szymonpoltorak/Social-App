package razepl.dev.socialappbackend.exceptions;

public class UserAlreadyExistsException extends IllegalStateException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
