import { PostData } from "@interfaces/home/PostData";

/**
 * Represents the interface for managing home posts.
 */
export interface HomePostsInterface {
    /**
     * Creates a new post.
     */
    createNewPost(): void;

    /**
     * Deletes a post from the list of posts.
     *
     * @param postData - The post data to delete.
     */
    deletePostFromList(postData: PostData): void;

    /**
     * Updates the friend list associated with the home posts.
     */
    updateFriendList(): void;
}
