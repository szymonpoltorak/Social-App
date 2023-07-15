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
     * The mapping for authentication endpoints.
     */
    public static final String AUTH_MAPPING = "/auth/";

    /**
     * The URL for logging out.
     */
    public static final String LOGOUT_URL = "/api/auth/logout";

    /**
     * The Ant matcher for authentication endpoints.
     */
    private static final String AUTH_MATCHERS = "/api/auth/**";

    /**
     * Swagger json address.
     */
    private static final String SWAGGER_JSON = "/v3/api-docs";

    /**
     * Swagger json matching urls.
     */
    private static final String SWAGGER_JSON_MATCHERS = "/v3/api-docs/**";

    /**
     * Swagger UI url.
     */
    private static final String SWAGGER_UI = "/swagger-ui.html";

    /**
     * Swagger UI matching urls.
     */
    private static final String SWAGGER_UI_MATCHERS = "/swagger-ui/**";

    /**
     * Admin endpoints matcher.
     */
    public static final String ADMIN_MATCHERS = "/api/admin/**";

    /**
     * List of URLs that should be excluded from authentication requirements.
     */
    public static final String[] WHITE_LIST = {AUTH_MATCHERS, SWAGGER_JSON,
            SWAGGER_JSON_MATCHERS, SWAGGER_UI, SWAGGER_UI_MATCHERS, "/api/test/**", "/login/oauth2/**"};

    /**
     * Private constructor to prevent instantiation of this class.
     */
    private Headers() {
    }
}
