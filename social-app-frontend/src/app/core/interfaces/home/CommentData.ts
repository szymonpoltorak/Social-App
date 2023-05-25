/**
 * Represents comment data.
 */
export interface CommentData {
    /**
     * The author of the comment.
     */
    commentAuthor: string;

    /**
     * The content of the comment.
     */
    commentContent: string;

    /**
     * The unique identifier of the comment.
     */
    commentId: number;

    /**
     * The date of the comment.
     */
    commentDate: Date;

    /**
     * Indicates whether the comment is liked or not.
     */
    isCommentLiked: boolean;

    /**
     * The number of likes the comment has received.
     */
    numOfLikes: number;
}
