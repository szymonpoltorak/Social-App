import { Observable } from "rxjs";
import { PostData } from "@core/interfaces/home/PostData";
import { LikeResponse } from "@core/interfaces/home/LikeResponse";

/**
 * Represents the interface for the Post service.
 */
export interface PostServiceInterface {
    /**
     * Creates a new post.
     *
     * @param postContent The content of the post.
     * @returns An Observable of PostData representing the newly created post.
     */
    createNewPost(postContent: string): Observable<PostData>;

    /**
     * Retrieves a list of posts.
     *
     * @param numOfSite number of site used for pagination
     * @returns An Observable of an array of PostData representing the list of posts.
     */
    getListOfPosts(numOfSite: number): Observable<PostData[]>;

    /**
     * Manages the friend status of a user.
     *
     * @param friendsUsername The username of the friend.
     * @param url The URL for managing the friend status.
     * @returns An Observable of string representing the status response.
     */
    manageFriendStatus(friendsUsername: string, url: string): Observable<string>;

    /**
     * Updates the number of likes for a post.
     *
     * @param postId The ID of the post.
     * @returns An Observable of LikeResponse representing the updated like response.
     */
    updateNumOfLikes(postId: number): Observable<LikeResponse>;

    /**
     * Deletes a post.
     *
     * @param postId The ID of the post to delete.
     * @returns An Observable of void.
     */
    deletePost(postId: number): Observable<void>;
}
