package razepl.dev.socialappbackend.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.entities.friend.FriendsRepository;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.exceptions.validators.ArgumentValidator;
import razepl.dev.socialappbackend.search.data.UserSearchData;
import razepl.dev.socialappbackend.search.interfaces.SearchServiceInterface;

import java.util.List;

/**
 * Service class for a Search Controller in /api/search endpoints.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService implements SearchServiceInterface {
    private static final int PAGE_SIZE = 100;
    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;

    @Override
    public final List<UserSearchData> getListOfUserBasedOnPattern(String pattern, int numOfSite, User user) {
        ArgumentValidator.throwIfNull(pattern, user);
        ArgumentValidator.throwIfNegativeId(numOfSite);

        Pageable pageable = Pageable.ofSize(PAGE_SIZE).withPage(numOfSite);
        Page<User> users = userRepository.findAllByPattern(pattern, pageable, user);

        log.info("Got : {} users", users.getNumberOfElements());

        return users
                .stream()
                .map(usr -> UserSearchData
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
