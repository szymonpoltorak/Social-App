package razepl.dev.socialappbackend.moderator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.entities.comment.CommentRepository;
import razepl.dev.socialappbackend.entities.post.PostRepository;
import razepl.dev.socialappbackend.home.data.PostResponse;
import razepl.dev.socialappbackend.home.interfaces.HomeDataBuilderService;
import razepl.dev.socialappbackend.moderator.interfaces.ModeratorService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModeratorServiceImpl implements ModeratorService {
    private final PostRepository postRepository;
    private final HomeDataBuilderService dataService;
    private final CommentRepository commentRepository;

    @Override
    public final List<PostResponse> getPostsList() {
        log.info("Getting posts list");

        return postRepository
                .findAll()
                .stream()
                .map(post -> dataService.buildPostData(post, false, false))
                .toList();
    }

    @Override
    public final void deletePost(long postId) {
        log.info("Deleting post with id: {}", postId);

        postRepository.deleteById(postId);
    }

    @Override
    public final void updatePostContent(long postId, String newContent) {
        log.info("Updating post with id: {}", postId);
        log.info("New content: {}", newContent);

        postRepository
                .findById(postId)
                .ifPresent(post -> {
                    post.setPostContent(newContent);

                    postRepository.save(post);
                });
    }

    @Override
    public final void deleteComment(long commentId) {
        log.info("Deleting comment with id: {}", commentId);

        commentRepository.deleteById(commentId);
    }
}
