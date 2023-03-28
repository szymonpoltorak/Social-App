export const enum PasswordValidation {
    PASSWORD_MIN_LENGTH = 8,
    PASSWORD_MAX_LENGTH = 20,
    PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[:\\?\\.@!#:\\-_=+ ])[a-zA-Z0-9:\\?\\.@!#:\\-_=+ ]{8,}$",
    PASSWORD_VALUE = ''
}
