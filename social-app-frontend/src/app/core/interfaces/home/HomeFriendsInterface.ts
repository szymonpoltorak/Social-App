/**
 * Represents the interface for managing home friends.
 */
export interface HomeFriendsInterface {
    /**
     * Removes a friend from the list of friends.
     *
     * @param friendUsername - The username of the friend to remove.
     */
    removeFriendFromList(friendUsername: string): void;
}
