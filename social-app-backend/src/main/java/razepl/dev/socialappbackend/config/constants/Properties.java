package razepl.dev.socialappbackend.config.constants;

/**
 * This class contains constants for the JWT properties used in the authentication process.
 */
public final class Properties {
    /**
     * The property for the expiration time of the JWT token.
     */
    public static final String EXPIRATION_PROPERTY = "${security.jwt.expiration-time}";

    /**
     * The property for the encoding key of the JWT token.
     */
    public static final String ENCODING_KEY_PROPERTY = "${security.jwt.encoding-key}";

    /**
     * The property for the refresh time of the JWT token.
     */
    public static final String REFRESH_PROPERTY = "${security.jwt.refresh-time}";

    /**
     * Private constructor to prevent instantiation of this class.
     */
    private Properties() {
    }
}
