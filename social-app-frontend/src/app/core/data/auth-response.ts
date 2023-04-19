/**
 * Represents the response received from the server upon successful authentication.
 */
export class AuthResponse {
    /**
     * The authorization token.
     */
    authToken!: string;

    /**
     * The refresh token.
     */
    refreshToken!: string;

    /**
     * Returns `true` if both `authToken` and `refreshToken` are empty strings, `false` otherwise.
     */
    isEmpty(): boolean {
        return this.authToken === "" || this.refreshToken === "";
    }
}
