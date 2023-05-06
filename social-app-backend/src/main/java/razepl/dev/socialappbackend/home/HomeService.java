package razepl.dev.socialappbackend.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.friend.Friend;
import razepl.dev.socialappbackend.friend.FriendsRepository;
import razepl.dev.socialappbackend.home.data.FriendData;
import razepl.dev.socialappbackend.home.data.UserData;
import razepl.dev.socialappbackend.home.interfaces.HomeServiceInterface;
import razepl.dev.socialappbackend.user.User;
import razepl.dev.socialappbackend.user.interfaces.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeService implements HomeServiceInterface {
    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;

    @Override
    public final UserData buildUserDataFromDb(String username) {
        User user = userRepository.findByEmail(username).orElseThrow();

        log.info("Building data for user : {}", user);

        return UserData
                .builder()
                .fullName(user.getFullName())
                .location(convertNullIntoEmptyString(user.getLocation()))
                .job(convertNullIntoEmptyString(user.getJob()))
                .github(convertNullIntoEmptyString(user.getGithub()))
                .linkedin(convertNullIntoEmptyString(user.getLinkedin()))
                .twitter(convertNullIntoEmptyString(user.getTwitter()))
                .numOfFriends(friendsRepository.countFriendByUser(user))
                .build();
    }

    @Override
    public List<FriendData> buildUsersFriendList(String username) {
        User user = userRepository.findByEmail(username).orElseThrow();
        List<Friend> friendList = friendsRepository.findAllByUser(user).orElseThrow();
        List<FriendData> response = new ArrayList<>(friendList.size());

        log.info("Friend list for user : {}", user);

        for (Friend friend : friendList) {
            FriendData friendData = FriendData
                    .builder()
                    .friendFullName(friend.getFriendName())
                    .friendUsername(friend.getFriendUsername())
                    .friendJob(convertNullIntoEmptyString(friend.getFriendJob()))
                    .build();
            response.add(friendData);
        }
        return response;
    }

    private String convertNullIntoEmptyString(String value) {
        return value == null ? "" : value;
    }
}
