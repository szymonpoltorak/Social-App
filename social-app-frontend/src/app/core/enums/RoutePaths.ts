export const enum RoutePaths {
    CURRENT_PATH = "",

    LOGIN_MODULE = '../login/login.module',
    LOGIN_AUTH_MODULE = './login/login.module',
    LOGIN_AUTH_PATH = "login",
    LOGIN_PATH = 'auth/login',

    REGISTER_MODULE = '../register/register.module',
    REGISTER_AUTH_MODULE = './register/register.module',
    REGISTER_PATH = 'auth/register',
    REGISTER_AUTH_PATH = "register",

    AUTH_PATH = "auth",
    HOME_PATH = "home",
    NOT_FOUND_PATH = "error",
    ERROR_MATCHER = "**",

    AUTH_MODULE = "./pages/auth/auth.module",
    HOME_MODULE = "./pages/home/home.module",
    NOT_FOUND_MODULE = "./pages/not-found/not-found.module"
}
