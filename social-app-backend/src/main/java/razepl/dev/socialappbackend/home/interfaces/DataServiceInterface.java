package razepl.dev.socialappbackend.home.interfaces;

import razepl.dev.socialappbackend.entities.comment.Comment;
import razepl.dev.socialappbackend.entities.post.Post;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.CommentData;
import razepl.dev.socialappbackend.home.data.LikeData;
import razepl.dev.socialappbackend.home.data.PostData;
import razepl.dev.socialappbackend.home.data.UserData;

public interface DataServiceInterface {
    UserData buildUserData(User user);

    PostData buildPostData(Post post, boolean isUserInFriends, boolean isPostLiked);

    LikeData buidLikeData(boolean isPostLiked, Post post);

    LikeData buildLikeData(boolean isPostLiked, Comment comment);

    CommentData buildCommentData(Comment comment, User user);
}
