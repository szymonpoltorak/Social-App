package razepl.dev.socialappbackend.home;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.entities.comment.Comment;
import razepl.dev.socialappbackend.entities.comment.CommentRepository;
import razepl.dev.socialappbackend.entities.commentlike.CommentLikeRepository;
import razepl.dev.socialappbackend.entities.friend.FriendsRepository;
import razepl.dev.socialappbackend.entities.post.Post;
import razepl.dev.socialappbackend.entities.postlike.PostLikeRepository;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.CommentResponse;
import razepl.dev.socialappbackend.home.data.LikeResponse;
import razepl.dev.socialappbackend.home.data.PostResponse;
import razepl.dev.socialappbackend.home.data.UserResponse;
import razepl.dev.socialappbackend.home.interfaces.HomeDataBuilderService;
import razepl.dev.socialappbackend.validators.ArgumentValidator;

@Service
@RequiredArgsConstructor
public class HomeDataBuilderServiceImpl implements HomeDataBuilderService {
    private final PostLikeRepository postLikeRepository;
    private final FriendsRepository friendsRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Override
    public final UserResponse buildUserData(User user) {
        ArgumentValidator.throwIfNull(user);

        return UserResponse
                .builder()
                .fullName(user.getFullName())
                .location(convertNullIntoEmptyString(user.getLocation()))
                .job(convertNullIntoEmptyString(user.getJob()))
                .github(convertNullIntoEmptyString(user.getGithub()))
                .linkedin(convertNullIntoEmptyString(user.getLinkedin()))
                .twitter(convertNullIntoEmptyString(user.getTwitter()))
                .numOfFriends(friendsRepository.countFriendByUser(user))
                .build();
    }

    @Override
    public final PostResponse buildPostData(Post post, boolean isUserInFriends, boolean isPostLiked) {
        ArgumentValidator.throwIfNull(post);

        return PostResponse
                .builder()
                .postAuthor(post.getUser().getFullName())
                .username(post.getUser().getUsername())
                .postContent(post.getPostContent())
                .postDate(post.getPostDate())
                .isUserInFriends(isUserInFriends)
                .numOfLikes(postLikeRepository.countByPost(post))
                .numOfComments(commentRepository.countCommentsByPost(post))
                .isPostLiked(isPostLiked)
                .postId(post.getPostId())
                .build();
    }

    @Override
    public final LikeResponse buidLikeData(boolean isPostLiked, Post post) {
        ArgumentValidator.throwIfNull(post);

        return LikeResponse
                .builder()
                .numOfLikes(postLikeRepository.countByPost(post))
                .isLiked(isPostLiked)
                .build();
    }

    @Override
    public final LikeResponse buildLikeData(boolean isPostLiked, Comment comment) {
        ArgumentValidator.throwIfNull(comment);

        return LikeResponse
                .builder()
                .numOfLikes(commentLikeRepository.countByComment(comment))
                .isLiked(isPostLiked)
                .build();
    }

    @Override
    public final CommentResponse buildCommentData(Comment comment, User user) {
        ArgumentValidator.throwIfNull(comment, user);

        return CommentResponse
                .builder()
                .commentAuthor(comment.getUser().getFullName())
                .commentContent(comment.getCommentContent())
                .commentDate(comment.getCommentDate())
                .commentId(comment.getCommentId())
                .isCommentLiked(commentLikeRepository.findByUserAndComment(user, comment).isPresent())
                .numOfLikes(commentLikeRepository.countByComment(comment))
                .build();
    }

    private String convertNullIntoEmptyString(String value) {
        return value == null ? "" : value;
    }
}
