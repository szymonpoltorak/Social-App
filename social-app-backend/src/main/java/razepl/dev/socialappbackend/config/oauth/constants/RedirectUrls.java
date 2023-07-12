package razepl.dev.socialappbackend.config.oauth.constants;

public final class RedirectUrls {
    public static final String FAILURE_URL = "http://localhost:4200/auth/login";
    public static final String SUCCESS_URL = "http://localhost:8080/login";
    public static final String OAUTH_URL = "http://localhost:8080/oauth2/authorization";

    private RedirectUrls() {
    }
}
