package razepl.dev.socialappbackend.admin.interfaces;

import razepl.dev.socialappbackend.home.data.UserData;

import java.util.List;

/**
 * Represents the service interface for admin-related operations.
 */
public interface AdminService {
    /**
     * Retrieves a list of user data for all users.
     *
     * @return The list of user data.
     */
    List<UserData> getUsersList();

    /**
     * Deletes a user with the specified email.
     *
     * @param email - The email of the user to delete.
     */
    void deleteUser(String email);

    /**
     * Updates the name of a user with the specified email.
     *
     * @param email - The email of the user to update.
     * @param newName - The new name for the user.
     */
    void updateUsersName(String email, String newName);
}
