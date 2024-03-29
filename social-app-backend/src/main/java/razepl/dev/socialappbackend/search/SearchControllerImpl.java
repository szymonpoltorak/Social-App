package razepl.dev.socialappbackend.search;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.search.data.UserSearchResponse;
import razepl.dev.socialappbackend.search.interfaces.SearchController;
import razepl.dev.socialappbackend.search.interfaces.SearchService;

import java.util.List;

import static razepl.dev.socialappbackend.search.constants.SearchMapping.SEARCH_SITE_MAPPING;
import static razepl.dev.socialappbackend.search.constants.SearchMapping.USERS_LIST_MAPPING;

/**
 * Controller for /api/search endpoints
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = SEARCH_SITE_MAPPING)
@Tag(name = "Searching for users")
public class SearchControllerImpl implements SearchController {
    private final SearchService searchService;

    @Override
    @GetMapping(value = USERS_LIST_MAPPING)
    public final List<UserSearchResponse> getTheListOfUsers(@RequestParam String pattern, @RequestParam int numOfSite,
                                                            @AuthenticationPrincipal User user) {
        return searchService.getListOfUserBasedOnPattern(pattern, numOfSite, user);
    }
}
