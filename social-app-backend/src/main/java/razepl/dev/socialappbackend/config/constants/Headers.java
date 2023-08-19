package razepl.dev.socialappbackend.config.constants;

/**
 * This class contains constants for headers used in the authentication process.
 */
public final class Headers {
    /**
     * The name of the header containing the authentication information.
     */
    public static final String AUTH_HEADER = "Authorization";

    /**
     * The prefix for the authentication token in the header.
     */
    public static final String TOKEN_HEADER = "Bearer ";

    /**
     * The starting index for the authentication token in the header.
     */
    public static final int TOKEN_START_INDEX = 7;

    /**
     * Private constructor to prevent instantiation of this class.
     */
    private Headers() {
    }
}
