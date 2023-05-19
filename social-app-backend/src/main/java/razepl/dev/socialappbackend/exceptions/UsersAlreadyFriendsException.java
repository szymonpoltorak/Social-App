package razepl.dev.socialappbackend.exceptions;

/**
 * This class represents an exception that is thrown when two users are already friends.
 * It is a subclass of IllegalArgumentException.
 */
public class UsersAlreadyFriendsException extends IllegalArgumentException {
    /**
     * Constructs a new UsersAlreadyFriendsException with the specified detail message.
     *
     * @param message The detail message.
     */
    public UsersAlreadyFriendsException(String message) {
        super(message);
    }
}
