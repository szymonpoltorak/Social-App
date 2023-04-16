package razepl.dev.socialappbackend.auth.constants;

/**
 * A utility class that contains constants for authentication mappings.
 */
public final class AuthMappings {
    /**
     * The base mapping for authentication endpoints.
     */
    public static final String AUTH_MAPPING = "/api/auth/";

    /**
     * The mapping for user registration endpoint.
     */
    public static final String REGISTER_MAPPING = "/register";

    /**
     * The mapping for user login endpoint.
     */
    public static final String LOGIN_MAPPING = "/login";

    /**
     * The mapping for user login endpoint.
     */
    public static final String REFRESH_MAPPING = "/refreshToken";

    /**
     * A private constructor to prevent instantiation of this class.
     */
    private AuthMappings() {
    }
}
