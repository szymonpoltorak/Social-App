package razepl.dev.socialappbackend.home.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.home.data.FriendUserRequest;
import razepl.dev.socialappbackend.home.data.UserDataRequest;

public interface HomeUserInterface {
    ResponseEntity<String> addToUsersFriends(FriendUserRequest request);

    ResponseEntity<String> removeFromUsersFriends(FriendUserRequest request);

    ResponseEntity<String> updateTwitterData(UserDataRequest request);

    ResponseEntity<String> updateLinkedinData(UserDataRequest request);

    ResponseEntity<String> updateGithubData(UserDataRequest request);

    ResponseEntity<String> updateUsersLocation(UserDataRequest request);

    ResponseEntity<String> updateUsersJob(UserDataRequest request);
}
