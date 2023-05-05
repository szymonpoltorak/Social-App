package razepl.dev.socialappbackend.home.interfaces;

import razepl.dev.socialappbackend.home.data.UserDataRequest;

public interface UserServiceInterface {
    void updateTwitterData(UserDataRequest request);

    void updateLinkedinData(UserDataRequest request);

    void updateGithubData(UserDataRequest request);

    void updateUsersLocation(UserDataRequest request);

    void updateUsersJob(UserDataRequest request);
}
