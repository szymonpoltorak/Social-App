/**
 * The interface for the SocialNavbar.
 * Provides methods for user interactions with the social navigation bar.
 */
export interface SocialNavbarInterface {

    /**
     * Logs out the user from the site.
     * This method does not return any value.
     */
    logoutUserFromSite(): void;

    /**
     * Performs a user search.
     * This method does not return any value.
     */
    searchForUser(): void;

    /**
     * Navigates to a specific URL.
     * This method does not return any value.
     */
    navigateToUrl(): void;
}
