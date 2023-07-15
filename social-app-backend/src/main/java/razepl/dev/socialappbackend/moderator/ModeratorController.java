package razepl.dev.socialappbackend.moderator;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import razepl.dev.socialappbackend.home.data.PostData;

import java.util.List;

import static razepl.dev.socialappbackend.moderator.Mappings.*;

@RestController
@RequestMapping(value = MODERATOR_MAPPING)
@RequiredArgsConstructor
public class ModeratorController implements IModeratorController {
    private final IModeratorService moderatorService;

    @Override
    @GetMapping(value = POSTS_MAPPING)
    public final List<PostData> getPostsList() {
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
