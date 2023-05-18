/**
 * Represents data about a post.
 */
export interface PostData {
    /**
     * The content of the post.
     */
    postContent: string;

    /**
     * The author of the post.
     */
    postAuthor: string;

    /**
     * The date of the post.
     */
    postDate: Date;

    /**
     * The username associated with the post.
     */
    username: string;

    /**
     * The number of likes on the post.
     */
    numOfLikes: number;

    /**
     * The number of comments on the post.
     */
    numOfComments: number;

    /**
     * The ID of the post.
     */
    postId: number;

    /**
     * Indicates whether the post is liked.
     */
    isPostLiked: boolean;

    /**
     * Indicates whether the user is friends with the author of the post.
     */
    isUserInFriends: boolean;
}
