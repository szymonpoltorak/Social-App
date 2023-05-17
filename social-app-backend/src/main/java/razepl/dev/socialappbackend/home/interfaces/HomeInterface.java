package razepl.dev.socialappbackend.home.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.*;

import java.util.List;

/**
 * This interface provides methods to interact with a home page.
 */
public interface HomeInterface {
    /**
     * Retrieves user data for a given user.
     *
     * @param user The user for whom to retrieve the data.
     * @return A ResponseEntity containing the user data.
     */
    ResponseEntity<UserData> getUserData(User user);

    /**
     * Retrieves a list of friend data for a given user.
     *
     * @param user The user for whom to retrieve the friend list.
     * @return A ResponseEntity containing the list of friend data.
     */
    ResponseEntity<List<FriendData>> getFriendsList(User user);

    /**
     * Retrieves a list of post data with an offset value for a given user.
     *
     * @param offsetValue The offset value for pagination.
     * @param user The user for whom to retrieve the post list.
     * @return A ResponseEntity containing the list of post data.
     */
    ResponseEntity<List<PostData>> getPostsList(int offsetValue, User user);

    /**
     * Creates a new post with the specified content for a given user.
     *
     * @param postContent The content of the post to create.
     * @param user The user who is creating the post.
     * @return A ResponseEntity containing the created post data.
     */
    ResponseEntity<PostData> createPost(String postContent, User user);

    /**
     * Changes the number of likes for a specific post for a given user.
     *
     * @param postId The ID of the post for which to change the number of likes.
     * @param user The user who is changing the number of likes.
     * @return A ResponseEntity containing the updated like data.
     */
    ResponseEntity<LikeData> changePostNumberOfLikes(long postId, User user);

    /**
     * Deletes a specific post for a given user.
     *
     * @param postId The ID of the post to delete.
     * @return A ResponseEntity indicating the success of the deletion.
     */
    ResponseEntity<Void> deletePost(long postId);
}

