package razepl.dev.socialappbackend.home.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.DataResponse;

/**
 * This interface provides methods for managing user-related operations in a home page.
 */
public interface HomeUserController {
    /**
     * Adds a user to the friend list of the specified user.
     *
     * @param friendsUsername The username of the friend to add.
     * @param user            The user who is adding the friend.
     * @return A ResponseEntity containing the response data.
     */
    @Operation(
            summary = "Patch endpoint for add user to users friends",
            description = "Add user to current users friends",
            responses = {
                    @ApiResponse(
                            description = "Successfully added to friends",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    DataResponse addToUsersFriends(String friendsUsername, User user);

    /**
     * Removes a user from the friend list of the specified user.
     *
     * @param friendsUsername The username of the friend to remove.
     * @param user            The user who is removing the friend.
     * @return A ResponseEntity containing the response data.
     */
    @Operation(
            summary = "Patch endpoint for remove user from users friends",
            description = "Add user to current users friends",
            responses = {
                    @ApiResponse(
                            description = "Successfully removed user from friends",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    DataResponse removeFromUsersFriends(String friendsUsername, User user);

    /**
     * Updates the Twitter data for the specified user.
     *
     * @param updateData The updated Twitter data.
     * @param user       The user whose Twitter data is being updated.
     * @return A ResponseEntity containing the response data.
     */
    @Operation(
            summary = "Patch endpoint for user twitter data",
            description = "Used to update twitter data",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated data",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    DataResponse updateTwitterData(String updateData, User user);

    /**
     * Updates the LinkedIn data for the specified user.
     *
     * @param updateData The updated LinkedIn data.
     * @param user       The user whose LinkedIn data is being updated.
     * @return A ResponseEntity containing the response data.
     */
    @Operation(
            summary = "Patch endpoint for user linkedin data",
            description = "Used to update linkedin data",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated data",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    DataResponse updateLinkedinData(String updateData, User user);

    /**
     * Updates the GitHub data for the specified user.
     *
     * @param updateData The updated GitHub data.
     * @param user       The user whose GitHub data is being updated.
     * @return A ResponseEntity containing the response data.
     */
    @Operation(
            summary = "Patch endpoint for user github data",
            description = "Used to update github data",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated data",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    DataResponse updateGithubData(String updateData, User user);

    /**
     * Updates the location data for the specified user.
     *
     * @param updateData The updated location data.
     * @param user       The user whose location data is being updated.
     * @return A ResponseEntity containing the response data.
     */
    @Operation(
            summary = "Patch endpoint for user location data",
            description = "Used to update location data",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated data",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    DataResponse updateUsersLocation(String updateData, User user);

    /**
     * Updates the job data for the specified user.
     *
     * @param updateData The updated job data.
     * @param user       The user whose job data is being updated.
     * @return A ResponseEntity containing the response data.
     */
    @Operation(
            summary = "Patch endpoint for user job data",
            description = "Used to update job data",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated data",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "user",
                    hidden = true
            )
    )
    DataResponse updateUsersJob(String updateData, User user);
}
