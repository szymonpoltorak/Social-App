package razepl.dev.socialappbackend.config.oauth.constants;

/**
 * This class defines constants for attribute names used by different authentication providers.
 */
public final class ProvidersAttributes {
    /**
     * Attribute name for the full name of a GitHub user.
     */
    public static final String GITHUB_FULL_NAME = "name";

    /**
     * Attribute name for the login of a GitHub user.
     */
    public static final String GITHUB_LOGIN = "login";

    /**
     * Attribute name for the location of a GitHub user.
     */
    public static final String GITHUB_LOCATION = "location";

    /**
     * Attribute name for the token of a GitHub user.
     */
    public static final String GITHUB_TOKEN = "node_id";

    /**
     * Attribute name for the name of a Google user.
     */
    public static final String GOOGLE_NAME = "given_name";

    /**
     * Attribute name for the family name of a Google user.
     */
    public static final String GOOGLE_FAMILY_NAME = "family_name";

    /**
     * Attribute name for the login of a Google user.
     */
    public static final String GOOGLE_LOGIN = "email";

    /**
     * Attribute name for the token of a Google user.
     */
    public static final String GOOGLE_TOKEN = "aud";

    private ProvidersAttributes() {
    }
}
