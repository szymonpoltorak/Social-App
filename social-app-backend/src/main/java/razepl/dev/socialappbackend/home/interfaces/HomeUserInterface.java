package razepl.dev.socialappbackend.home.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.home.data.DataResponse;
import razepl.dev.socialappbackend.home.data.FriendUserRequest;
import razepl.dev.socialappbackend.home.data.UserDataRequest;

public interface HomeUserInterface {
    ResponseEntity<DataResponse> addToUsersFriends(FriendUserRequest request);

    ResponseEntity<DataResponse> removeFromUsersFriends(FriendUserRequest request);

    ResponseEntity<DataResponse> updateTwitterData(UserDataRequest request);

    ResponseEntity<DataResponse> updateLinkedinData(UserDataRequest request);

    ResponseEntity<DataResponse> updateGithubData(UserDataRequest request);

    ResponseEntity<DataResponse> updateUsersLocation(UserDataRequest request);

    ResponseEntity<DataResponse> updateUsersJob(UserDataRequest request);
}
