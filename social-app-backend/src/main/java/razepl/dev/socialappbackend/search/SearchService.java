package razepl.dev.socialappbackend.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.home.data.FriendData;
import razepl.dev.socialappbackend.search.interfaces.SearchServiceInterface;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService implements SearchServiceInterface {
    private static final int PAGE_SIZE = 100;
    private final UserRepository userRepository;

    @Override
    public final List<FriendData> getListOfUserBasedOnPattern(String pattern, int numOfSite) {
        Pageable pageable = Pageable.ofSize(PAGE_SIZE).withPage(numOfSite);
        Page<User> users = userRepository.findAllByPattern(pattern, pageable);

        log.info("Got : {} users", users.getNumberOfElements());

        return users
                .stream()
                .map(usr -> FriendData
                        .builder()
                        .friendUsername(usr.getUsername())
                        .friendFullName(usr.getFullName())
                        .friendJob(convertNullIntoEmptyString(usr.getJob()))
                        .build())
                .collect(Collectors.toList());
    }

    private String convertNullIntoEmptyString(String value) {
        return value == null ? "" : value;
    }
}
