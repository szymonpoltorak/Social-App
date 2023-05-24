package razepl.dev.socialappbackend.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.home.data.FriendData;
import razepl.dev.socialappbackend.search.interfaces.SearchInterface;
import razepl.dev.socialappbackend.search.interfaces.SearchServiceInterface;

import java.util.List;

import static razepl.dev.socialappbackend.search.constants.SearchMapping.SEARCH_SITE_MAPPING;
import static razepl.dev.socialappbackend.search.constants.SearchMapping.USERS_LIST_MAPPING;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = SEARCH_SITE_MAPPING)
public class SearchController implements SearchInterface {
    private final SearchServiceInterface searchService;

    @Override
    @GetMapping(value = USERS_LIST_MAPPING)
    public final ResponseEntity<List<FriendData>> getTheListOfUsers(@RequestParam String pattern,
                                                                    @RequestParam int numOfSite) {
        log.info("Getting list of users based on pattern : {}", pattern);

        return ResponseEntity.ok(searchService.getListOfUserBasedOnPattern(pattern, numOfSite));
    }
}