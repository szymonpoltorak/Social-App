package razepl.dev.socialappbackend.search.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.search.data.UserSearchResponse;

import java.util.List;

/**
 * An interface for searching users and retrieving a list of user search data.
 */
public interface SearchController {
    /**
     * Retrieves the list of users matching the specified search pattern.
     *
     * @param pattern   The search pattern to match against users names or emails.
     * @param numOfSite The number of users to retrieve per page.
     * @param user      The authenticated user making the request.
     * @return A ResponseEntity containing the list of user search data.
     */
    @Operation(
            summary = "Get endpoint for getting list of users matching pattern",
            description = "Returns list of users based on pattern and number of pageable site",
            responses = {
                    @ApiResponse(
                            description = "Success, returns list of users",
                            responseCode = "200"
                    )
            }

    )
    List<UserSearchResponse> getTheListOfUsers(String pattern, int numOfSite, User user);
}
