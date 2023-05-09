export const enum LinkValidation {
    LINK_REGEX = "^(?=.{16,})(?:(?:https?|ftp):\\/\\/)?(?:www\\.)?[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,}(?:\\/[\\w#]*)*\\/?$",
    LINK_VALUE = ''
}
