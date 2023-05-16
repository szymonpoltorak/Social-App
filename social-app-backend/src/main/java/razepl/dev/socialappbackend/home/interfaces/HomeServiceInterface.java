package razepl.dev.socialappbackend.home.interfaces;

import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.*;

import java.util.List;

public interface HomeServiceInterface {
    UserData buildUserDataFromDb(String username);

    List<FriendData> buildUsersFriendList(String username);

    List<PostData> getTheListOfPostsByNumberOfSite(int numOfSite, User user);

    PostData createNewPost(PostRequest request);

    LikeResponse updatePostLikeCounter(LikeRequest request);

    void deletePostByPostId(long postId);
}
