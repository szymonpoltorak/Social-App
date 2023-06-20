package razepl.dev.socialappbackend.search.interfaces;

import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.search.data.UserSearchData;

import java.util.List;

/**
 * An interface for searching users and retrieving a list of user search data.
 */
public interface SearchInterface {
    /**
     * Retrieves the list of users matching the specified search pattern.
     *
     * @param pattern    The search pattern to match against users names or emails.
     * @param numOfSite  The number of users to retrieve per page.
     * @param user       The authenticated user making the request.
     * @return A ResponseEntity containing the list of user search data.
     */
    List<UserSearchData> getTheListOfUsers(String pattern, int numOfSite, User user);
}
