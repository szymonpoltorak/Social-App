/**
 * The interface for the Friend.
 * Provides methods to add and remove a user from friends.
 */
export interface FriendInterface {

    /**
     * Removes a user from the friends list.
     * This method does not return any value.
     */
    removeUserFromFriends(): void;

    /**
     * Adds a user to the friends list.
     * This method does not return any value.
     */
    addUserToFriends(): void;
}
