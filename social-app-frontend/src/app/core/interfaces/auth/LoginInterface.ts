/**
 * Interface for implementing login functionality.
 */
export interface LoginInterface {
    /**
     * Authenticates the user by calling the backend API with the provided login credentials.
     * Upon successful authentication, redirects the user to the home page.
     * Otherwise, displays an error message.
     */
    authenticateUser(): void;
}
