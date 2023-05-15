package razepl.dev.socialappbackend.exceptions;

public class UsersAlreadyFriendsException extends IllegalArgumentException {
    public UsersAlreadyFriendsException(String message) {
        super(message);
    }
}
