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

        return UserData
                .builder()
                .fullName(user.getFullName())
                .location(user.getLocation())
                .job(user.getJob())
                .github(user.getGithub())
                .linkedin(user.getLinkedin())
                .twitter(user.getTwitter())
                .numOfFriends(friendsRepository.countFriendByUser(user))
                .build();
    }

    @Override
    public List<FriendData> buildUsersFriendList(String username) {
        User user = userRepository.findByEmail(username).orElseThrow();
        List<Friend> friendList = friendsRepository.findAllByUser(user).orElseThrow();
        List<FriendData> response = new ArrayList<>(friendList.size());

        for (Friend friend : friendList) {
            FriendData friendData = FriendData
                    .builder()
                    .fullName(friend.getFriendName())
                    .job(friend.getFriendJob())
                    .build();
            response.add(friendData);
        }
        return response;
    }
}
