import { RegisterRequest } from "../../data/register-request";
import { LoginRequest } from "../../data/login-request";
import { Observable } from "rxjs";
import { AuthResponse } from "../../data/auth-response";
import { TokenResponse } from "../../data/token-response";

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
    registerUser(registerRequest: RegisterRequest): Observable<AuthResponse>;

    /**
     * Logs in a user by making a POST request to the authentication API's login endpoint with the provided login request data.
     * If the request is successful, the response data containing the user's authentication and refresh tokens will be added to
     * local storage.
     *
     * @param loginRequest The login request data to send in the request body.
     */
    loginUser(loginRequest: LoginRequest): Observable<AuthResponse>;

    /**
     * Sends a POST request to the authentication API's refresh endpoint with the provided refresh token to obtain a new authentication
     * token. If the request is successful, the response data containing the new authentication and refresh tokens will be added to
     * local storage, and the current authentication and refresh tokens will be removed.
     *
     * @param refreshToken The refresh token to send in the request body.
     */
    refreshUsersToken(refreshToken: string): Observable<AuthResponse>;

    /**
     * Checks if the user is authenticated.
     *
     * @returns An Observable emitting a TokenResponse.
     */
    isUserAuthenticated(): Observable<TokenResponse>;

    /**
     * Saves the authentication data.
     *
     * @param data - The authentication response data to be saved.
     */
    saveData(data: AuthResponse): void;

    /**
     * Logs out the user.
     *
     * @returns An Observable emitting any result after logout.
     */
    logoutUser(): Observable<any>;
}
