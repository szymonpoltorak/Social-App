package razepl.dev.socialappbackend.search.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.home.data.FriendData;

import java.util.List;

public interface SearchInterface {
    ResponseEntity<List<FriendData>> getTheListOfUsers(String pattern, int numOfSite);
}
