package razepl.dev.socialappbackend.exceptions;

public class TokenDoesNotExistException extends IllegalArgumentException{
    public TokenDoesNotExistException(String message) {
        super(message);
    }
}
