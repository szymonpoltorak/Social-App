export const enum AuthApiCalls {
    REGISTER_URL = "/api/auth/register",
    LOGIN_URL = "/api/auth/login",
    REFRESH_URL = "/api/auth/refreshToken",

    IS_USER_AUTHENTICATED = "/api/auth/authenticate",

    AUTH_ERROR_FOUND = '{"authToken": "", "refreshToken": ""}',
    TOKEN_ERROR_FOUND = `{"isAuthTokenValid": "false", "isRefreshTokenValid": false}`
}
