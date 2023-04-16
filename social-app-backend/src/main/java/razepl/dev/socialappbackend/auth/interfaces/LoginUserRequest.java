package razepl.dev.socialappbackend.auth.interfaces;

/**
 * This interface represents a request to log in a user.
 */
public interface LoginUserRequest {
    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    String getUsername();

    /**
     * Returns the password of the user.
     *
     * @return The password of the user.
     */
    String getPassword();
}
