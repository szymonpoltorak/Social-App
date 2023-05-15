package razepl.dev.socialappbackend.home.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.home.data.*;

import java.util.List;

public interface HomeInterface {
    ResponseEntity<UserData> getUserData(String username);

    ResponseEntity<List<FriendData>> getFriendsList(String username);

    ResponseEntity<List<PostData>> getPostsList(int offsetValue);

    ResponseEntity<PostData> createPost(PostRequest request);

    ResponseEntity<DataResponse> changePostNumberOfLikes(LikeRequest request);

    ResponseEntity<Void> deletePost(long postId);
}
