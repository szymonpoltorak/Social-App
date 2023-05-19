import { Observable } from "rxjs";

/**
 * Represents the interface for the User Home service.
 */
export interface UserHomeInterface {
    /**
     * Updates the user's data.
     *
     * @param updateData The updated data.
     * @param url The URL for updating the user's data.
     * @returns An Observable of string representing the update response.
     */
    updateUsersData(updateData: string, url: string): Observable<string>;

    /**
     * Manages the endpoints for the user's friend.
     *
     * @param friendsUsername The username of the friend.
     * @param url The URL for managing the friend endpoints.
     * @returns An Observable of string representing the status response.
     */
    manageUsersFriendEndpoints(friendsUsername: string, url: string): Observable<string>;
}
