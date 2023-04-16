package razepl.dev.socialappbackend.config.constants;

/**
 * This class contains constants for headers used in the authentication process.
 */
public final class Headers {
    /**
     * The mapping for authentication endpoints.
     */
    public static final String AUTH_MAPPING = "/auth/";

    /**
     * The name of the header containing the authentication information.
     */
    public static final String AUTH_HEADER = "Authorization";

    /**
     * The prefix for the authentication token in the header.
     */
    public static final String TOKEN_HEADER = "Bearer ";

    /**
     * The Ant matcher for authentication endpoints.
     */
    public static final String AUTH_MATCHERS = "/api/auth/**";

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
