package razepl.dev.socialappbackend.search.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.search.data.UserSearchData;

import java.util.List;

public interface SearchInterface {
    ResponseEntity<List<UserSearchData>> getTheListOfUsers(String pattern, int numOfSite, User user);
}
