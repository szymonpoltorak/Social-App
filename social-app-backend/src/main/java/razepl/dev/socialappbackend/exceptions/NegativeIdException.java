package razepl.dev.socialappbackend.exceptions;

public class NegativeIdException extends IllegalArgumentException {
    public NegativeIdException(String message) {
        super(message);
    }
}
