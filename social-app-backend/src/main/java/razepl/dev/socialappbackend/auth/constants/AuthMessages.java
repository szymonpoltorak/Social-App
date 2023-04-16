package razepl.dev.socialappbackend.auth.constants;

/**
 * The AuthMessages class contains constant string messages related to user authentication.
 * These messages are used to provide feedback to users during the authentication process.
 */
public final class AuthMessages {

    /**
     * Message indicating that a new user has been added to the user repository.
     */
    public static final String ADDED_INFO = "Added user to userRepository.";

    /**
     * Message indicating that a user has been successfully registered.
     */
    public static final String SUCCESSFUL_REGISTER = "User has been successfully registered!";

    /**
     * Delimiter string used to separate error messages.
     */
    public static final String ERROR_DELIMITER = "\n";

    /**
     * Format string used to construct error messages. This string takes two arguments:
     * the error code and the error message.
     */
    public static final String ERROR_FORMAT = "%s : %s";

    /**
     * Private constructor to prevent instantiation of the AuthMessages class.
     */
    private AuthMessages() {
    }
}
