package razepl.dev.socialappbackend.home;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.exceptions.validators.NullChecker;
import razepl.dev.socialappbackend.home.data.*;
import razepl.dev.socialappbackend.home.interfaces.HomeInterface;
import razepl.dev.socialappbackend.home.interfaces.HomeServiceInterface;

import java.util.List;

import static razepl.dev.socialappbackend.home.constants.HomeMappings.*;

/**
 * Controller for /api/home endpoints.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = HOME_MAPPING)
@Tag(name = "Home site data provider")
public class HomeController implements HomeInterface {
    private final HomeServiceInterface homeService;

    @Override
    @GetMapping(value = USERDATA_MAPPING)
    public final ResponseEntity<UserData> getUserData(@AuthenticationPrincipal User user) {
        NullChecker.throwIfNull(user);

        log.info("Getting userdata for user : {}", user);

        return ResponseEntity.ok(homeService.buildUserDataFromDb(user));
    }

    @Override
    @GetMapping(value = FRIENDS_LIST_MAPPING)
    public final ResponseEntity<List<FriendData>> getFriendsList(@AuthenticationPrincipal User user) {
        NullChecker.throwIfNull(user);

        log.info("Finding list of users for : {}", user);

        return ResponseEntity.ok(homeService.buildUsersFriendList(user));
    }

    @Override
    @GetMapping(value = POST_LIST_MAPPING)
    public final ResponseEntity<List<PostData>> getPostsList(@RequestParam int numOfSite,
                                                             @AuthenticationPrincipal User user) {
        log.info("Number of site for posts : {}", numOfSite);
        log.info("User that sent request: {}", user);

        return ResponseEntity.ok(homeService.getTheListOfPostsByNumberOfSite(numOfSite, user));
    }

    @Override
    @PostMapping(value = CREATE_POST_MAPPING)
    public final ResponseEntity<PostData> createPost(@RequestParam String postContent, User user) {
        NullChecker.throwIfNull(postContent);

        log.info("Creating post with data : {}", postContent);
        log.info("User who wants to create post : {}", user);

        return ResponseEntity.ok(homeService.createNewPost(postContent, user));
    }

    @Override
    @PatchMapping(value = LIKE_POST_MAPPING)
    public final ResponseEntity<LikeData> changePostNumberOfLikes(@RequestParam long postId,
                                                                  @AuthenticationPrincipal User user) {
        log.info("User wants to change number of like with data : {}", postId);

        return ResponseEntity.ok(homeService.updatePostLikeCounter(postId, user));
    }

    @Override
    @DeleteMapping(value = DELETE_POST_MAPPING)
    public final ResponseEntity<Void> deletePost(@RequestParam long postId) {
        log.info("Removing post of id : {}", postId);

        homeService.deletePostByPostId(postId);

        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(value = GET_COMMENTS_MAPPING)
    public final ResponseEntity<List<CommentData>> getListOfComments(@RequestParam long postId,
                                                                     @RequestParam int numOfSite,
                                                                     @AuthenticationPrincipal User user) {
        log.info("Get list of comments for post of id: {}", postId);
        log.info("Num Of Site : {}", numOfSite);

        return ResponseEntity.ok(homeService.getListOfComments(postId, numOfSite, user));
    }

    @Override
    @PostMapping(value = CREATE_COMMENT_MAPPING)
    public final ResponseEntity<CommentData> createComment(@RequestBody CommentRequest request,
                                                           @AuthenticationPrincipal User user) {
        log.info("Creating comment with data: {}\nOf User: {}", request, user);

        return ResponseEntity.ok(homeService.createComment(request, user));
    }

    @Override
    @PatchMapping(value = COMMENT_LIKE_MAPPING)
    public final ResponseEntity<LikeData> changeCommentNumberOfLikes(@RequestParam long commentId,
                                                                     @AuthenticationPrincipal User user) {
        log.info("Creating like for comment of id : {}\nBy User : {}", commentId, user);

        return ResponseEntity.ok(homeService.updateCommentLikeCounter(commentId, user));
    }
}
