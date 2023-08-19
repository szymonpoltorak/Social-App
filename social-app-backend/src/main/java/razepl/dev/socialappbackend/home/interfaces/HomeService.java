package razepl.dev.socialappbackend.home.interfaces;

import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.*;

import java.util.List;

/**
 * This interface provides methods to interact with the home service.
 */
public interface HomeService {
    /**
     * Builds user data from the database for a given user.
     *
     * @param user The user for whom to build the user data.
     * @return The user data built from the database.
     */
    UserResponse buildUserDataFromDb(User user);

    /**
     * Builds the list of friend data for a given user.
     *
     * @param user The user for whom to build the friend list.
     * @return The list of friend data.
     */
    List<FriendResponse> buildUsersFriendList(User user);

    /**
     * Retrieves a list of post data by the number of site for a given user.
     *
     * @param numOfSite The number of site for pagination.
     * @param user      The user for whom to retrieve the post list.
     * @return The list of post data.
     */
    List<PostResponse> getTheListOfPostsByNumberOfSite(int numOfSite, User user);

    /**
     * Creates a new post with the specified content for a given user.
     *
     * @param postContent The content of the post to create.
     * @param user        The user who is creating the post.
     * @return The created post data.
     */
    PostResponse createNewPost(String postContent, User user);

    /**
     * Updates the like counter for a specific post for a given user.
     *
     * @param postId The ID of the post for which to update the like counter.
     * @param user   The user who is updating the like counter.
     * @return The updated like data.
     */
    LikeResponse updatePostLikeCounter(long postId, User user);

    /**
     * Deletes a specific post by its ID.
     *
     * @param postId The ID of the post to delete.
     */
    void deletePostByPostId(long postId);

    /**
     * Retrieves a list of comments for a post.
     *
     * @param postId    - The ID of the post.
     * @param numOfSite - The number of comments per page.
     * @param user      - The user making the request.
     * @return A list of CommentData objects representing the comments.
     */
    List<CommentResponse> getListOfComments(long postId, int numOfSite, User user);

    /**
     * Creates a new comment for a post.
     *
     * @param request - The CommentRequest object containing the comment details.
     * @param user    - The user creating the comment.
     * @return The created CommentData object.
     */
    CommentResponse createComment(CommentRequest request, User user);

    /**
     * Updates the number of likes for a comment.
     *
     * @param commentId - The ID of the comment.
     * @param user      - The user making the request.
     * @return The updated LikeData object.
     */
    LikeResponse updateCommentLikeCounter(long commentId, User user);
}

