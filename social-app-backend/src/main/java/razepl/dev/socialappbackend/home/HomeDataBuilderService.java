package razepl.dev.socialappbackend.home;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.entities.comment.Comment;
import razepl.dev.socialappbackend.entities.comment.CommentRepository;
import razepl.dev.socialappbackend.entities.commentlike.CommentLikeRepository;
import razepl.dev.socialappbackend.entities.friend.FriendsRepository;
import razepl.dev.socialappbackend.entities.postlike.PostLikeRepository;
import razepl.dev.socialappbackend.entities.post.Post;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.CommentData;
import razepl.dev.socialappbackend.home.data.LikeData;
import razepl.dev.socialappbackend.home.data.PostData;
import razepl.dev.socialappbackend.home.data.UserData;
import razepl.dev.socialappbackend.home.interfaces.DataServiceInterface;

@Service
@RequiredArgsConstructor
public class HomeDataBuilderService implements DataServiceInterface {
    private final PostLikeRepository postLikeRepository;
    private final FriendsRepository friendsRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Override
    public final UserData buildUserData(User user) {
        return UserData
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
    public final PostData buildPostData(Post post, boolean isUserInFriends, boolean isPostLiked) {
        return PostData
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
    public final LikeData buidLikeData(boolean isPostLiked, Post post) {
        return LikeData
                .builder()
                .numOfLikes(postLikeRepository.countByPost(post))
                .isPostLiked(isPostLiked)
                .build();
    }

    @Override
    public final LikeData buildLikeData(boolean isPostLiked, Comment comment) {
        return LikeData
                .builder()
                .numOfLikes(commentLikeRepository.countByComment(comment))
                .isPostLiked(isPostLiked)
                .build();
    }

    @Override
    public final CommentData buildCommentData(Comment comment, User user) {
        return CommentData
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
