package razepl.dev.socialappbackend.config.oauth.constants;

import org.springframework.beans.factory.annotation.Value;

/**
 * This class defines constants for redirect URLs used in the authentication process.
 */
public final class RedirectUrls {
    @Value("${server.frontend_url}")
    private static final String FRONTEND_URL = "";

    /**
     * The URL to redirect to in case of authentication failure.
     */
    public static final String FAILURE_URL = "http://localhost:4200/auth/login";

    /**
     * The URL to redirect to in case of authentication success.
     */
    public static final String SUCCESS_URL = "http://localhost:4200/oauth";

    private RedirectUrls() {
    }
}
