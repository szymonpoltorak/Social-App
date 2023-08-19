package razepl.dev.socialappbackend.home;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.*;
import razepl.dev.socialappbackend.home.interfaces.HomeController;
import razepl.dev.socialappbackend.home.interfaces.HomeService;

import java.util.List;

import static razepl.dev.socialappbackend.home.constants.HomeMappings.*;

/**
 * Controller for /api/home endpoints.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = HOME_MAPPING)
@Tag(name = "Home site data provider")
public class HomeControllerImpl implements HomeController {
    private final HomeService homeService;

    @Override
    @GetMapping(value = USERDATA_MAPPING)
    public final UserResponse getUserData(@AuthenticationPrincipal User user) {
        return homeService.buildUserDataFromDb(user);
    }

    @Override
    @GetMapping(value = FRIENDS_LIST_MAPPING)
    public final List<FriendResponse> getFriendsList(@AuthenticationPrincipal User user) {
        return homeService.buildUsersFriendList(user);
    }

    @Override
    @GetMapping(value = POST_LIST_MAPPING)
    public final List<PostResponse> getPostsList(@RequestParam int numOfSite, @AuthenticationPrincipal User user) {
        return homeService.getTheListOfPostsByNumberOfSite(numOfSite, user);
    }

    @Override
    @PostMapping(value = CREATE_POST_MAPPING)
    public final PostResponse createPost(@RequestParam String postContent, @AuthenticationPrincipal User user) {
        return homeService.createNewPost(postContent, user);
    }

    @Override
    @PatchMapping(value = LIKE_POST_MAPPING)
    public final LikeResponse changePostNumberOfLikes(@RequestParam long postId, @AuthenticationPrincipal User user) {
        return homeService.updatePostLikeCounter(postId, user);
    }

    @Override
    @DeleteMapping(value = DELETE_POST_MAPPING)
    public final void deletePost(@RequestParam long postId) {
        homeService.deletePostByPostId(postId);
    }

    @Override
    @GetMapping(value = GET_COMMENTS_MAPPING)
    public final List<CommentResponse> getListOfComments(@RequestParam long postId, @RequestParam int numOfSite,
                                                         @AuthenticationPrincipal User user) {
        return homeService.getListOfComments(postId, numOfSite, user);
    }

    @Override
    @PostMapping(value = CREATE_COMMENT_MAPPING)
    public final CommentResponse createComment(@RequestBody CommentRequest request, @AuthenticationPrincipal User user) {
        return homeService.createComment(request, user);
    }

    @Override
    @PatchMapping(value = COMMENT_LIKE_MAPPING)
    public final LikeResponse changeCommentNumberOfLikes(@RequestParam long commentId, @AuthenticationPrincipal User user) {
        return homeService.updateCommentLikeCounter(commentId, user);
    }
}
