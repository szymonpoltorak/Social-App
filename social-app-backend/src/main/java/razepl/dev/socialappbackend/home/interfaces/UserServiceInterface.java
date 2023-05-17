package razepl.dev.socialappbackend.home.interfaces;

import razepl.dev.socialappbackend.entities.user.User;

public interface UserServiceInterface {
    void updateTwitterData(String updateData, User user);

    void updateLinkedinData(String updateData, User user);

    void updateGithubData(String updateData, User user);

    void updateUsersLocation(String updateData, User user);

    void updateUsersJob(String updateData, User user);

    void removeFriendFromUser(String friendsUsername, User user);

    void addFriendToUser(String friendsUsername, User user);
}
