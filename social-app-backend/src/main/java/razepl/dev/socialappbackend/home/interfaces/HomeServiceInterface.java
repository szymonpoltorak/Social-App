package razepl.dev.socialappbackend.home.interfaces;

import razepl.dev.socialappbackend.home.data.*;

import java.util.List;

public interface HomeServiceInterface {
    UserData buildUserDataFromDb(String username);

    List<FriendData> buildUsersFriendList(String username);

    List<PostData> getTheListOfPostsByNumberOfSite(int numOfSite);

    PostData createNewPost(PostRequest request);
}
