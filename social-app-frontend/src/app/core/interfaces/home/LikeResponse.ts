/**
 * Represents the response data for a like operation.
 */
export interface LikeResponse {
    /**
     * The number of likes.
     */
    numOfLikes: number;

    /**
     * Indicates whether the post is liked.
     */
    isPostLiked: boolean;
}
