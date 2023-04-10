package razepl.dev.socialappbackend.auth.constants;

/**
 * A utility class that contains constants for authentication mappings.
 */
public final class AuthMappings {
    /**
     * The part of the request path that represents the user entity.
     */
    public static final String USER_REQUEST_PART = "user";

    /**
     * The base mapping for authentication endpoints.
     */
    public static final String AUTH_MAPPING = "/auth/";

    /**
     * The mapping for user registration endpoint.
     */
    public static final String REGISTER_MAPPING = "/register";

    /**
     * A private constructor to prevent instantiation of this class.
     */
    private AuthMappings() {
    }
}
