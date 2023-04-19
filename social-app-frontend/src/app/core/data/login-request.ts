/**
 * Represents a login request to be sent to the server.
 */
export class LoginRequest {
    /**
     * The username of the user logging in.
     */
    username!: string;

    /**
     * The password of the user logging in.
     */
    password!: string;
}
