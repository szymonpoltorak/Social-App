import { RegisterRequest } from "../data/register-request";
import { LoginRequest } from "../data/login-request";

/**
 * The interface for Auth Service.
 */
export interface AuthInterface {
    /**
     * Registers a new user by making a POST request to the authentication API's registration endpoint with the provided
     * registration request data. If the request is successful, the response data containing the user's authentication and
     * refresh tokens will be added to local storage.
     *
     * @param registerRequest The registration request data to send in the request body.
     */
    registerUser(registerRequest: RegisterRequest): void;

    /**
     * Logs in a user by making a POST request to the authentication API's login endpoint with the provided login request data.
     * If the request is successful, the response data containing the user's authentication and refresh tokens will be added to
     * local storage.
     *
     * @param loginRequest The login request data to send in the request body.
     */
    loginUser(loginRequest: LoginRequest): void;

    /**
     * Sends a POST request to the authentication API's refresh endpoint with the provided refresh token to obtain a new authentication
     * token. If the request is successful, the response data containing the new authentication and refresh tokens will be added to
     * local storage, and the current authentication and refresh tokens will be removed.
     *
     * @param refreshToken The refresh token to send in the request body.
     */
    refreshUsersToken(refreshToken: string): void;

    isUserAuthenticated(): boolean;
}
