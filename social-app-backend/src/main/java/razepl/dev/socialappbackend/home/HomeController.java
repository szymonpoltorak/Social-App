package razepl.dev.socialappbackend.home;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import razepl.dev.socialappbackend.exceptions.validators.NullChecker;
import razepl.dev.socialappbackend.home.data.*;
import razepl.dev.socialappbackend.home.interfaces.HomeInterface;
import razepl.dev.socialappbackend.home.interfaces.HomeServiceInterface;

import java.util.List;

import static razepl.dev.socialappbackend.home.constants.HomeMappings.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = HOME_MAPPING)
@Tag(name = "Home site data provider")
public class HomeController implements HomeInterface {
    private final HomeServiceInterface homeService;

    @Override
    @GetMapping(value = USERDATA_MAPPING)
    public final ResponseEntity<UserData> getUserData(@RequestParam String username) {
        NullChecker.throwAppropriateException(username);

        log.info("Getting userdata for user : {}", username);

        return ResponseEntity.ok(homeService.buildUserDataFromDb(username));
    }

    @Override
    @GetMapping(value = FRIENDS_LIST_MAPPING)
    public final ResponseEntity<List<FriendData>> getFriendsList(@RequestParam String username) {
        NullChecker.throwAppropriateException(username);

        log.info("Finding list of users for : {}", username);

        return ResponseEntity.ok(homeService.buildUsersFriendList(username));
    }

    @Override
    @GetMapping(value = POST_LIST_MAPPING)
    public final ResponseEntity<List<PostData>> getPostsList(@RequestParam int numOfSite) {
        log.info("Number of site for posts : {}", numOfSite);

        return ResponseEntity.ok(homeService.getTheListOfPostsByNumberOfSite(numOfSite));
    }

    @Override
    @PostMapping(value = CREATE_POST_MAPPING)
    public final ResponseEntity<PostData> createPost(@RequestBody PostRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Creating post with data : {}", request);

        return ResponseEntity.ok(homeService.createNewPost(request));
    }

    @Override
    @PatchMapping(value = LIKE_POST_MAPPING)
    public final ResponseEntity<DataResponse> changePostNumberOfLikes(@RequestBody LikeRequest request) {
        log.info("User wants to change number of like with data : {}", request);

        return ResponseEntity.ok(homeService.updatePostLikeCounter(request));
    }
}
