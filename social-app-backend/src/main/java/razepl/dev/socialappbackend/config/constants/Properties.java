package razepl.dev.socialappbackend.config.constants;

public final class Properties {
    public static final String EXPIRATION_PROPERTY = "${security.jwt.expiration-time}";

    public static final String ENCODING_KEY_PROPERTY = "${security.jwt.encoding-key}";

    public static final String REFRESH_PROPERTY = "${security.jwt.refresh-time}";

    private Properties() {
    }
}
