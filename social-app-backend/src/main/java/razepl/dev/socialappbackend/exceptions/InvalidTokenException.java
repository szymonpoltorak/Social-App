package razepl.dev.socialappbackend.exceptions;

public class InvalidTokenException extends IllegalArgumentException{
    public InvalidTokenException(String message) {
        super(message);
    }
}
