package razepl.dev.socialappbackend.home.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.*;

import java.util.List;

/**
 * This interface provides methods to interact with a home page.
 */
public interface HomeController {
    /**
     * Retrieves user data for a given user.
     *
     * @param user The user for whom to retrieve the data.
     * @return A ResponseEntity containing the user data.
     */
    @Operation(
            summary = "Get endpoint for getting user data",
            description = "Returns essential data about the user",
            responses = {
                    @ApiResponse(
                            description = "Successfully returned data",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    UserData getUserData(User user);

    /**
     * Retrieves a list of friend data for a given user.
     *
     * @param user The user for whom to retrieve the friend list.
     * @return A ResponseEntity containing the list of friend data.
     */
    @Operation(
            summary = "Get endpoint for getting list of users friends",
            description = "Returns list of friends of current user",
            responses = {
                    @ApiResponse(
                            description = "Successfully returned data",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    List<FriendData> getFriendsList(User user);

    /**
     * Retrieves a list of post data with an offset value for a given user.
     *
     * @param numOfSite The offset value for pagination.
     * @param user      The user for whom to retrieve the post list.
     * @return A ResponseEntity containing the list of post data.
     */
    @Operation(
            summary = "Get endpoint for getting list of posts based on page",
            description = "Returns list of posts on current page",
            responses = {
                    @ApiResponse(
                            description = "Successfully returned data",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    List<PostData> getPostsList(int numOfSite, User user);

    /**
     * Creates a new post with the specified content for a given user.
     *
     * @param postContent The content of the post to create.
     * @param user        The user who is creating the post.
     * @return A ResponseEntity containing the created post data.
     */
    @Operation(
            summary = "Post endpoint for creating new post entity",
            description = "Creates new post in database and returns PostData of it",
            responses = {
                    @ApiResponse(
                            description = "Successfully returned data",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    PostData createPost(String postContent, User user);

    /**
     * Changes the number of likes for a specific post for a given user.
     *
     * @param postId The ID of the post for which to change the number of likes.
     * @param user   The user who is changing the number of likes.
     * @return A ResponseEntity containing the updated like data.
     */
    @Operation(
            summary = "Patch endpoint for updating like of post",
            description = "Patches the number of likes on post of given postId",
            responses = {
                    @ApiResponse(
                            description = "Successfully patched data",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    LikeData changePostNumberOfLikes(long postId, User user);

    /**
     * Deletes a specific post for a given user.
     *
     * @param postId The ID of the post to delete.
     */
    @Operation(
            summary = "Delete endpoint for deleting post from database",
            description = "Deletes post from database based on given postId",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted post",
                            responseCode = "200"
                    )
            }
    )
    void deletePost(long postId);

    /**
     * Retrieves a list of comments for a post.
     *
     * @param postId    - The ID of the post.
     * @param numOfSite - The number of comments per page.
     * @param user      - The user making the request.
     * @return A ResponseEntity containing a list of CommentData objects representing the comments.
     */
    @Operation(
            summary = "Get endpoint for getting list of comments",
            description = "Returning list of comments of a given post on the given site",
            responses = {
                    @ApiResponse(
                            description = "Successfully got comments",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    List<CommentData> getListOfComments(long postId, int numOfSite, User user);

    /**
     * Creates a new comment for a post.
     *
     * @param request - The CommentRequest object containing the comment details.
     * @param user    - The user creating the comment.
     * @return A ResponseEntity containing the created CommentData object.
     */
    @Operation(
            summary = "Post endpoint for creating post",
            description = "Creates new comment by the user and for the given postId",
            responses = {
                    @ApiResponse(
                            description = "Successfully created comment",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    CommentData createComment(CommentRequest request, User user);

    /**
     * Changes the number of likes for a comment.
     *
     * @param commentId - The ID of the comment.
     * @param user      - The user making the request.
     * @return A ResponseEntity containing the updated LikeData object.
     */
    @Operation(
            summary = "Patch endpoint for updating number of likes on comment",
            description = "Changes number of likes for comment of given id",
            responses = {
                    @ApiResponse(
                            description = "Successfully changed number of likes",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    LikeData changeCommentNumberOfLikes(long commentId, User user);
}

