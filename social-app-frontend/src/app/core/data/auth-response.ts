/**
 * Represents the response received from the server upon successful authentication.
 */
export interface AuthResponse {
    /**
     * The authorization token.
     */
    authToken: string;

    /**
     * The refresh token.
     */
    refreshToken: string;
}
