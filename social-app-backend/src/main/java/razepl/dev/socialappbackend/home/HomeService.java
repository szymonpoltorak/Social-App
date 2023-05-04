package razepl.dev.socialappbackend.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.friend.FriendsRepository;
import razepl.dev.socialappbackend.home.data.UserData;
import razepl.dev.socialappbackend.home.interfaces.HomeServiceInterface;
import razepl.dev.socialappbackend.user.User;
import razepl.dev.socialappbackend.user.interfaces.UserRepository;

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
                .fullName(String.format("%s %s", user.getName(), user.getSurname()))
                .location(user.getLocation())
                .job(user.getJob())
                .github(user.getGithub())
                .linkedin(user.getLinkedin())
                .twitter(user.getTwitter())
                .numOfFriends(friendsRepository.count())
                .build();
    }
}
