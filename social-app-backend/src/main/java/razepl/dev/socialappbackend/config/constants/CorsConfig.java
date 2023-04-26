package razepl.dev.socialappbackend.config.constants;

import java.util.List;

public final class CorsConfig {
    public static final List<String> ALLOWED_REQUESTS = List.of("GET","POST", "PUT", "DELETE", "OPTIONS");

    /**
     * The address of the frontend server.
     */
    public static final List<String> FRONTEND_ADDRESS = List.of("http://localhost:4200");

    public static final String CONTENT_TYPE_HEADER = "Content-Type";

    public static final String API_PATTERN = "/api/**";

    private CorsConfig() {
    }
}
