package razepl.dev.socialappbackend.config.constants;

public final class Matchers {
    /**
     * The mapping for authentication endpoints.
     */
    public static final String AUTH_MAPPING = "/auth/";

    /**
     * The URL for logging out.
     */
    public static final String LOGOUT_URL = "/api/auth/logout";
    /**
     * Admin endpoints matcher.
     */
    public static final String ADMIN_MATCHERS = "/api/admin/**";
    /**
     * Moderator endpoints matcher.
     */
    public static final String MODERATOR_MATCHERS = "/api/moderator/**";
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
     * List of URLs that should be excluded from authentication requirements.
     */
    public static final String[] WHITE_LIST = {AUTH_MATCHERS, SWAGGER_JSON,
            SWAGGER_JSON_MATCHERS, SWAGGER_UI, SWAGGER_UI_MATCHERS, "/api/test/**", "/login/oauth2/**",
            "/oauth2/authorization/github", "/oauth2/authorization/google"
    };

    private Matchers() {
    }
}
