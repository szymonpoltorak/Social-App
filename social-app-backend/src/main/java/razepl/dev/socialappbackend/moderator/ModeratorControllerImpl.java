package razepl.dev.socialappbackend.moderator;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.home.data.PostResponse;
import razepl.dev.socialappbackend.moderator.interfaces.ModeratorController;
import razepl.dev.socialappbackend.moderator.interfaces.ModeratorService;

import java.util.List;

import static razepl.dev.socialappbackend.moderator.Mappings.COMMENTS_MAPPING;
import static razepl.dev.socialappbackend.moderator.Mappings.MODERATOR_MAPPING;
import static razepl.dev.socialappbackend.moderator.Mappings.POSTS_MAPPING;

@RestController
@RequestMapping(value = MODERATOR_MAPPING)
@RequiredArgsConstructor
public class ModeratorControllerImpl implements ModeratorController {
    private final ModeratorService moderatorService;

    @Override
    @GetMapping(value = POSTS_MAPPING)
    public final List<PostResponse> getPostsList() {
        return moderatorService.getPostsList();
    }

    @Override
    @DeleteMapping(value = POSTS_MAPPING)
    public final void deletePost(@RequestParam long postId) {
        moderatorService.deletePost(postId);
    }

    @Override
    @PatchMapping(value = POSTS_MAPPING)
    public final void updatePostContent(@RequestParam long postId, @RequestParam String newContent) {
        moderatorService.updatePostContent(postId, newContent);
    }

    @Override
    @DeleteMapping(value = COMMENTS_MAPPING)
    public final void deleteComment(@RequestParam long commentId) {
        moderatorService.deleteComment(commentId);
    }
}
