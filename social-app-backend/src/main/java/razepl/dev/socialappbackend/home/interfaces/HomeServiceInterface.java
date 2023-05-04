package razepl.dev.socialappbackend.home.interfaces;

import razepl.dev.socialappbackend.home.data.UserData;

public interface HomeServiceInterface {
    UserData buildUserDataFromDb(String username);
}
