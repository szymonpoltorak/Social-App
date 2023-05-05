package razepl.dev.socialappbackend.home.interfaces;

import razepl.dev.socialappbackend.home.data.FriendData;
import razepl.dev.socialappbackend.home.data.UserData;

import java.util.List;

public interface HomeServiceInterface {
    UserData buildUserDataFromDb(String username);

    List<FriendData> buildUsersFriendList(String username);
}
