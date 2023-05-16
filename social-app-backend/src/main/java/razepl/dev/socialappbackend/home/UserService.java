package razepl.dev.socialappbackend.home;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.exceptions.UsersAlreadyFriendsException;
import razepl.dev.socialappbackend.entities.friend.Friend;
import razepl.dev.socialappbackend.entities.friend.FriendsRepository;
import razepl.dev.socialappbackend.home.data.FriendUserRequest;
import razepl.dev.socialappbackend.home.data.UserDataRequest;
import razepl.dev.socialappbackend.home.interfaces.UserServiceInterface;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;

    @Override
    public final void updateTwitterData(UserDataRequest request) {
        User user = userRepository.findByEmail(request.username()).orElseThrow();

        user.setTwitter(request.updateData());

        userRepository.save(user);
    }

    @Override
    public final void updateLinkedinData(UserDataRequest request) {
        User user = userRepository.findByEmail(request.username()).orElseThrow();

        user.setLinkedin(request.updateData());

        userRepository.save(user);
    }

    @Override
    public final void updateGithubData(UserDataRequest request) {
        User user = userRepository.findByEmail(request.username()).orElseThrow();

        user.setGithub(request.updateData());

        userRepository.save(user);
    }

    @Override
    public final void updateUsersLocation(UserDataRequest request) {
        User user = userRepository.findByEmail(request.username()).orElseThrow();

        user.setLocation(request.updateData());

        userRepository.save(user);
    }

    @Override
    public final void updateUsersJob(UserDataRequest request) {
        User user = userRepository.findByEmail(request.username()).orElseThrow();

        user.setJob(request.updateData());

        userRepository.save(user);
    }

    @Override
    public final void removeFriendFromUser(FriendUserRequest request) {
        User user = userRepository.findByEmail(request.username()).orElseThrow();
        Friend friend = friendsRepository.findByFriendUsernameAndUser(request.friendsUsername(), user).orElseThrow();

        friendsRepository.delete(friend);
    }

    @Override
    public final void addFriendToUser(FriendUserRequest request) {
        User user = userRepository.findByEmail(request.username()).orElseThrow();

        if (friendsRepository.findByFriendUsernameAndUser(request.friendsUsername(), user).isPresent()) {
            throw new UsersAlreadyFriendsException("User already exists!");
        }
        User friend = userRepository.findByEmail(request.friendsUsername()).orElseThrow();

        Friend newFriend = Friend
                .builder()
                .friendName(friend.getFullName())
                .friendUsername(friend.getUsername())
                .friendJob(friend.getJob())
                .user(user)
                .build();
        friendsRepository.save(newFriend);
    }
}
