package razepl.dev.socialappbackend.moderator;

import razepl.dev.socialappbackend.home.data.PostData;

import java.util.List;

/**
 * Represents the service interface for moderator-related operations.
 */
public interface IModeratorService {
    /**
     * Retrieves a list of post data for all posts.
     *
     * @return The list of post data.
     */
    List<PostData> getPostsList();

    /**
     * Deletes a post with the specified post ID.
     *
     * @param postId - The ID of the post to delete.
     */
    void deletePost(long postId);

    /**
     * Updates the content of a post with the specified post ID.
     *
     * @param postId - The ID of the post to update.
     * @param newContent - The new content for the post.
     */
    void updatePostContent(long postId, String newContent);

    /**
     * Deletes a comment with the specified comment ID.
     *
     * @param commentId - The ID of the comment to delete.
     */
    void deleteComment(long commentId);
}
