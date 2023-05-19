/**
 * Represents the interface for post functionality.
 */
export interface PostInterface {
    /**
     * Updates the post's like status.
     */
    updatePostLike(): void;

    /**
     * Updates the friend status of the post.
     */
    updateFriendStatus(): void;

    /**
     * Removes the post.
     */
    removePost(): void;
}
