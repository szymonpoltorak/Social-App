package razepl.dev.socialappbackend.search.interfaces;

import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.search.data.UserSearchResponse;

import java.util.List;

/**
 * An interface for searching users and retrieving a list of user search data.
 */
public interface SearchService {
    /**
     * Retrieves a list of users based on the specified search pattern.
     *
     * @param pattern   The search pattern to match against users names or emails.
     * @param numOfSite The number of users to retrieve per page.
     * @param user      The authenticated user making the request.
     * @return The list of user search data matching the search pattern.
     */
    List<UserSearchResponse> getListOfUserBasedOnPattern(String pattern, int numOfSite, User user);
}
