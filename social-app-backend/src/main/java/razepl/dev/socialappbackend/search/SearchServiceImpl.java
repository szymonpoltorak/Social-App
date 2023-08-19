package razepl.dev.socialappbackend.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.entities.friend.FriendsRepository;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.search.data.UserSearchResponse;
import razepl.dev.socialappbackend.search.interfaces.SearchService;
import razepl.dev.socialappbackend.validators.ArgumentValidator;

import java.util.List;

/**
 * Service class for a Search Controller in /api/search endpoints.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private static final int PAGE_SIZE = 100;
    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;

    @Override
    public final List<UserSearchResponse> getListOfUserBasedOnPattern(String pattern, int numOfSite, User user) {
        ArgumentValidator.throwIfNull(pattern, user);
        ArgumentValidator.throwIfNegativeId(numOfSite);

        log.info("Getting list of users based on pattern : {}\n On site : {}", pattern, numOfSite);

        Pageable pageable = Pageable.ofSize(PAGE_SIZE).withPage(numOfSite);
        Page<User> users = userRepository.findAllByPattern(pattern, pageable, user);

        log.info("Got : {} users", users.getNumberOfElements());

        return users
                .stream()
                .map(usr -> UserSearchResponse
                        .builder()
                        .username(usr.getUsername())
                        .fullName(usr.getFullName())
                        .job(convertNullIntoEmptyString(usr.getJob()))
                        .isUsersFriend(friendsRepository.findByFriendUsernameAndUser(usr.getUsername(), user).isPresent())
                        .build())
                .toList();
    }

    private String convertNullIntoEmptyString(String value) {
        return value == null ? "" : value;
    }
}
