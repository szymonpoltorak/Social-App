export const enum DialogContents {
    REGISTER_PARAGRAPH = "To make your form valid please ensure the following:",
    REGISTER_REQUIRED = "Every field is required,",
    REGISTER_YEAR = "You should be at least 13 years old,",
    REGISTER_PASSWORD = "Password should contain at least 8 and max 20 characters, " +
        "1 upper and lower case and 1 special character,",
    REGISTER_SAME_PASSWORDS = "Passwords must be the same,",

    FORM_HEADER = "Form is not valid!",

    LOGIN_PARAGRAPH = "You have filled form with wrong login and password, Please ensure the following:",
    LOGIN_EMAIL = "Check if your email is written correctly,",
    LOGIN_PASSWORD = "Check if you spelled your password correctly",

    REGISTER_USER_EXISTS_PARAGRAPH = "User already exists!",
    REGISTER_ITEMS = "If you forgot your password just use forgot password option!",

    LOGIN_WRONG_PARAGRAPH = "I was not able to log you in!",

    LOGGED_OUT_HEADER = "You were logged out!",
    LOGGED_OUT_PARAGRAPH = "There are possible reasons why you were logged out:",
    LOGGED_OUT_ERROR = "You were logout due to expiration of session",
    LOGGED_OUT_BUTTON = "You were logged out by using logout button"
}
