package razepl.dev.socialappbackend.moderator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.entities.comment.CommentRepository;
import razepl.dev.socialappbackend.entities.post.PostRepository;
import razepl.dev.socialappbackend.home.data.PostData;
import razepl.dev.socialappbackend.home.interfaces.DataServiceInterface;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModeratorService implements IModeratorService {
    private final PostRepository postRepository;
    private final DataServiceInterface dataService;
    private final CommentRepository commentRepository;

    @Override
    public final List<PostData> getPostsList() {
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
