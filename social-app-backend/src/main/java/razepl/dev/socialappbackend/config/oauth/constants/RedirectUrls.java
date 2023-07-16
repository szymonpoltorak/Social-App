package razepl.dev.socialappbackend.config.oauth.constants;

/**
 * This class defines constants for redirect URLs used in the authentication process.
 */
public final class RedirectUrls {
    /**
     * The URL to redirect to in case of authentication failure.
     */
    public static final String FAILURE_URL = "/auth/login";

    /**
     * The URL to redirect to in case of authentication success.
     */
    public static final String SUCCESS_URL = "/oauth";

    /**
     * The URL to redirect to in case of authentication success.
     */
    public static final String FRONTEND_URL_VALUE = "${frontend.url}";

    private RedirectUrls() {
    }
}
