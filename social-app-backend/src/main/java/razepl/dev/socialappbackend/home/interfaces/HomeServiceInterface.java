package razepl.dev.socialappbackend.home.interfaces;

import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.*;

import java.util.List;

public interface HomeServiceInterface {
    UserData buildUserDataFromDb(User user);

    List<FriendData> buildUsersFriendList(User user);

    List<PostData> getTheListOfPostsByNumberOfSite(int numOfSite, User user);

    PostData createNewPost(String postContent, User user);

    LikeResponse updatePostLikeCounter(long postId, User user);

    void deletePostByPostId(long postId);
}
