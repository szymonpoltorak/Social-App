package razepl.dev.socialappbackend.home;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.exceptions.FriendNotFoundException;
import razepl.dev.socialappbackend.exceptions.UsersAlreadyFriendsException;
import razepl.dev.socialappbackend.entities.friend.Friend;
import razepl.dev.socialappbackend.entities.friend.FriendsRepository;
import razepl.dev.socialappbackend.exceptions.validators.ArgumentValidator;
import razepl.dev.socialappbackend.home.interfaces.UserServiceInterface;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;

/**
 * Service class for /api/home/user controller.
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;

    @Override
    public final void updateTwitterData(String updateData, User user) {
        ArgumentValidator.throwIfNull(updateData);
        validateString(updateData);

        user.setTwitter(updateData);

        userRepository.save(user);
    }

    @Override
    public final void updateLinkedinData(String updateData, User user) {
        ArgumentValidator.throwIfNull(updateData);
        validateString(updateData);

        user.setLinkedin(updateData);

        userRepository.save(user);
    }

    @Override
    public final void updateGithubData(String updateData, User user) {
        ArgumentValidator.throwIfNull(updateData);
        validateString(updateData);

        user.setGithub(updateData);

        userRepository.save(user);
    }

    @Override
    public final void updateUsersLocation(String updateData, User user) {
        ArgumentValidator.throwIfNull(updateData);
        validateString(updateData);

        user.setLocation(updateData);

        userRepository.save(user);
    }

    @Override
    public final void updateUsersJob(String updateData, User user) {
        ArgumentValidator.throwIfNull(updateData);
        validateString(updateData);

        user.setJob(updateData);

        userRepository.save(user);
    }

    @Override
    public final void removeFriendFromUser(String friendsUsername, User user) {
        ArgumentValidator.throwIfNull(friendsUsername);

        Friend friend = friendsRepository.findByFriendUsernameAndUser(friendsUsername, user).orElseThrow(
                () -> new FriendNotFoundException("Friend does not exist!")
        );

        friendsRepository.delete(friend);
    }

    @Override
    public final void addFriendToUser(String friendsUsername, User user) {
        ArgumentValidator.throwIfNull(friendsUsername);

        if (friendsRepository.findByFriendUsernameAndUser(friendsUsername, user).isPresent()) {
            throw new UsersAlreadyFriendsException("User already exists!");
        }
        User friend = userRepository.findByEmail(friendsUsername).orElseThrow(
                () -> new UsernameNotFoundException("User does not exist!")
        );

        Friend newFriend = Friend
                .builder()
                .friendName(friend.getFullName())
                .friendUsername(friend.getUsername())
                .friendJob(friend.getJob())
                .user(user)
                .build();
        friendsRepository.save(newFriend);
    }

    private void validateString(String string) {
        if (string.isEmpty() || string.isBlank()) {
            throw new IllegalArgumentException("Value is empty or blank!");
        }
    }
}
