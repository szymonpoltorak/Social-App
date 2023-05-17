package razepl.dev.socialappbackend.home.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.DataResponse;

/**
 * This interface provides methods for managing user-related operations in a home page.
 */
public interface HomeUserInterface {
    /**
     * Adds a user to the friend list of the specified user.
     *
     * @param friendsUsername The username of the friend to add.
     * @param user The user who is adding the friend.
     * @return A ResponseEntity containing the response data.
     */
    ResponseEntity<DataResponse> addToUsersFriends(String friendsUsername, User user);

    /**
     * Removes a user from the friend list of the specified user.
     *
     * @param friendsUsername The username of the friend to remove.
     * @param user The user who is removing the friend.
     * @return A ResponseEntity containing the response data.
     */
    ResponseEntity<DataResponse> removeFromUsersFriends(String friendsUsername, User user);

    /**
     * Updates the Twitter data for the specified user.
     *
     * @param updateData The updated Twitter data.
     * @param user The user whose Twitter data is being updated.
     * @return A ResponseEntity containing the response data.
     */
    ResponseEntity<DataResponse> updateTwitterData(String updateData, User user);

    /**
     * Updates the LinkedIn data for the specified user.
     *
     * @param updateData The updated LinkedIn data.
     * @param user The user whose LinkedIn data is being updated.
     * @return A ResponseEntity containing the response data.
     */
    ResponseEntity<DataResponse> updateLinkedinData(String updateData, User user);

    /**
     * Updates the GitHub data for the specified user.
     *
     * @param updateData The updated GitHub data.
     * @param user The user whose GitHub data is being updated.
     * @return A ResponseEntity containing the response data.
     */
    ResponseEntity<DataResponse> updateGithubData(String updateData, User user);

    /**
     * Updates the location data for the specified user.
     *
     * @param updateData The updated location data.
     * @param user The user whose location data is being updated.
     * @return A ResponseEntity containing the response data.
     */
    ResponseEntity<DataResponse> updateUsersLocation(String updateData, User user);

    /**
     * Updates the job data for the specified user.
     *
     * @param updateData The updated job data.
     * @param user The user whose job data is being updated.
     * @return A ResponseEntity containing the response data.
     */
    ResponseEntity<DataResponse> updateUsersJob(String updateData, User user);
}
