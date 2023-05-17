package razepl.dev.socialappbackend.home.interfaces;

import razepl.dev.socialappbackend.entities.user.User;

/**
 * This interface provides methods for managing user-related operations.
 */
public interface UserServiceInterface {
    /**
     * Updates the Twitter data for the specified user.
     *
     * @param updateData The updated Twitter data.
     * @param user The user whose Twitter data is being updated.
     */
    void updateTwitterData(String updateData, User user);

    /**
     * Updates the LinkedIn data for the specified user.
     *
     * @param updateData The updated LinkedIn data.
     * @param user The user whose LinkedIn data is being updated.
     */
    void updateLinkedinData(String updateData, User user);

    /**
     * Updates the GitHub data for the specified user.
     *
     * @param updateData The updated GitHub data.
     * @param user The user whose GitHub data is being updated.
     */
    void updateGithubData(String updateData, User user);

    /**
     * Updates the location data for the specified user.
     *
     * @param updateData The updated location data.
     * @param user The user whose location data is being updated.
     */
    void updateUsersLocation(String updateData, User user);

    /**
     * Updates the job data for the specified user.
     *
     * @param updateData The updated job data.
     * @param user The user whose job data is being updated.
     */
    void updateUsersJob(String updateData, User user);

    /**
     * Removes a friend from the user's friend list.
     *
     * @param friendsUsername The username of the friend to remove.
     * @param user The user from whose friend list to remove the friend.
     */
    void removeFriendFromUser(String friendsUsername, User user);

    /**
     * Adds a friend to the user's friend list.
     *
     * @param friendsUsername The username of the friend to add.
     * @param user The user to whose friend list to add the friend.
     */
    void addFriendToUser(String friendsUsername, User user);
}

