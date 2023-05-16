package razepl.dev.socialappbackend.entities.user.constants;

/**
 * A utility class that contains constants for user validation messages.
 * The class defines the error messages for name, surname, date of birth, email and password fields.
 * The class has a private constructor to prevent instantiation.
 */
public final class UserValidationMessages {
    /**
     * The error message for name size validation.
     */
    public static final String NAME_SIZE_MESSAGE = "Name must be between {min} and {max} characters long";

    /**
     * The error message for name pattern validation.
     */
    public static final String NAME_PATTERN_MESSAGE = "Name must contain only alphabetic characters";

    /**
     * The error message for surname size validation.
     */
    public static final String SURNAME_SIZE_MESSAGE = "Surname must be between {min} and {max} characters long";

    /**
     * The error message for surname pattern validation.
     */
    public static final String SURNAME_PATTERN_MESSAGE = "Surname must contain only alphabetic characters";

    /**
     * The error message for date of birth not null validation.
     */
    public static final String DATE_NULL_MESSAGE = "Date of birth is mandatory";

    /**
     * The error message for email validation.
     */
    public static final String EMAIL_MESSAGE = "Email must be a valid email address";

    /**
     * The error message for password pattern validation.
     */
    public static final String PASSWORD_PATTERN_MESSAGE = "Password must contain at least one lowercase letter, one " +
            "uppercase letter, one digit and one special character from the following set: :?.@!#-_=+ ";

    /**
     * The error message for name not null validation.
     */
    public static final String NAME_NULL_MESSAGE = "Name is mandatory";

    /**
     * The error message for email not null validation.
     */
    public static final String EMAIL_NULL_MESSAGE = "Email is mandatory";

    /**
     * The error message for surname not null validation.
     */
    public static final String SURNAME_NULL_MESSAGE = "Surname is mandatory";

    /**
     * The error message for password not null validation.
     */
    public static final String PASSWORD_NULL_MESSAGE = "Password is mandatory";

    /**
     * A private constructor to prevent instantiation of this class.
     */
    private UserValidationMessages() {
    }
}
