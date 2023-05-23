import { Observable } from "rxjs";
import { LikeResponse } from "@interfaces/home/LikeResponse";
import { CommentData } from "@interfaces/home/CommentData";

/**
 * Represents the interface for managing comments.
 */
export interface CommentsServiceInterface {
    /**
     * Updates the number of likes for a comment.
     *
     * @param commentId - The ID of the comment.
     * @returns An Observable emitting the updated like response.
     */
    updateCommentNumOfLikes(commentId: number): Observable<LikeResponse>;

    /**
     * Retrieves a list of comments for a post.
     *
     * @param postId - The ID of the post.
     * @param numOfSite - The number of comments per page.
     * @returns An Observable emitting the list of comment data.
     */
    getListOfComments(postId: number, numOfSite: number): Observable<CommentData[]>;

    /**
     * Creates a new comment for a post.
     *
     * @param commentContent - The content of the comment.
     * @param postId - The ID of the post.
     * @returns An Observable emitting the created comment data.
     */
    createComment(commentContent: string, postId: number): Observable<CommentData>;
}
