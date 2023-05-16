package razepl.dev.socialappbackend.home.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.DataResponse;

public interface HomeUserInterface {
    ResponseEntity<DataResponse> addToUsersFriends(String friendsUsername, User user);

    ResponseEntity<DataResponse> removeFromUsersFriends(String friendsUsername, User user);

    ResponseEntity<DataResponse> updateTwitterData(String updateData, User user);

    ResponseEntity<DataResponse> updateLinkedinData(String updateData, User user);

    ResponseEntity<DataResponse> updateGithubData(String updateData, User user);

    ResponseEntity<DataResponse> updateUsersLocation(String updateData, User user);

    ResponseEntity<DataResponse> updateUsersJob(String updateData, User user);
}
