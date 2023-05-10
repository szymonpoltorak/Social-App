package razepl.dev.socialappbackend.home.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.home.data.FriendData;
import razepl.dev.socialappbackend.home.data.PostData;
import razepl.dev.socialappbackend.home.data.UserData;

import java.util.List;

public interface HomeInterface {
    ResponseEntity<UserData> getUserData(String username);

    ResponseEntity<List<FriendData>> getFriendsList(String username);

    ResponseEntity<List<PostData>> getPostsList(int offsetValue);
}
