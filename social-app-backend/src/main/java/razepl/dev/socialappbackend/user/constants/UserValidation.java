package razepl.dev.socialappbackend.user.constants;

/**
 * A utility class that provides constants and methods for validating user input.
 * The class defines patterns and length limits for email, name and password fields.
 * The class has a private constructor to prevent instantiation.
 */
public final class UserValidation {
    /**
     * The minimum length for a valid name.
     */
    public static final int NAME_MIN_LENGTH = 3;

    /**
     * The pattern for a valid date.
     */
    public static final String DATE_PATTERN = "dd-MM-yyyy";

    /**
     * The maximum length for a valid name.
     */
    public static final int NAME_MAX_LENGTH = 20;

    /**
     * The pattern for a valid name. It should contain only alphabetic characters.
     */
    public static final String NAME_PATTERN = "[a-zA-Z]+";

    /**
     * The minimum length for a valid password.
     */
    public static final int PASSWORD_MIN_LENGTH = 8;

    /**
     * The maximum length for a valid password.
     */
    public static final int PASSWORD_MAX_LENGTH = 20;

    /**
     * The pattern for a valid password. It should contain at least one lowercase letter, one uppercase letter, one
     * digit and one special character from the following set: :?.@!#-_=+
     */
    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[:\\?\\.@!#:\\-_=+ ])[a-zA-Z0-9:\\?\\.@!#:\\-_=+ ]{8,}$";

    public static final String USER_PACKAGE = "razepl.dev.socialappbackend.user.User";

    /**
     * A private constructor to prevent instantiation of this class.
     */
    private UserValidation() {
    }
}
