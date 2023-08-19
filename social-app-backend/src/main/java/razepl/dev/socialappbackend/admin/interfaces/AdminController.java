package razepl.dev.socialappbackend.admin.interfaces;

import razepl.dev.socialappbackend.auth.data.AuthResponse;
import razepl.dev.socialappbackend.auth.data.RegisterRequest;
import razepl.dev.socialappbackend.home.data.UserResponse;

import java.util.List;

/**
 * Represents the controller interface for admin-related operations.
 */
public interface AdminController {
    /**
     * Retrieves a list of user data for all users.
     *
     * @return The list of user data.
     */
    List<UserResponse> getUsersList();

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

    /**
     * Creates a new user based on the provided registration request.
     *
     * @param registerRequest - The registration request containing user details.
     * @return The authentication response for the created user.
     */
    AuthResponse createUser(RegisterRequest registerRequest);
}
