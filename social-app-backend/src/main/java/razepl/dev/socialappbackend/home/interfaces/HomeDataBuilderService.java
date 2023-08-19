package razepl.dev.socialappbackend.home.interfaces;

import razepl.dev.socialappbackend.entities.comment.Comment;
import razepl.dev.socialappbackend.entities.post.Post;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.CommentData;
import razepl.dev.socialappbackend.home.data.LikeData;
import razepl.dev.socialappbackend.home.data.PostData;
import razepl.dev.socialappbackend.home.data.UserData;

/**
 * Represents the interface for building data objects.
 */
public interface HomeDataBuilderService {
    /**
     * Builds a UserData object from a User.
     *
     * @param user - The User object to build from.
     * @return The built UserData object.
     */
    UserData buildUserData(User user);

    /**
     * Builds a PostData object from a Post, indicating whether the user is in friends and if the post is liked.
     *
     * @param post            - The Post object to build from.
     * @param isUserInFriends - Indicates whether the user is in friends.
     * @param isPostLiked     - Indicates whether the post is liked.
     * @return The built PostData object.
     */
    PostData buildPostData(Post post, boolean isUserInFriends, boolean isPostLiked);

    /**
     * Builds a LikeData object from a boolean indicating if the post is liked and a Post object.
     *
     * @param isPostLiked - Indicates whether the post is liked.
     * @param post        - The Post object to build from.
     * @return The built LikeData object.
     */
    LikeData buidLikeData(boolean isPostLiked, Post post);

    /**
     * Builds a LikeData object from a boolean indicating if the post is liked and a Comment object.
     *
     * @param isPostLiked - Indicates whether the post is liked.
     * @param comment     - The Comment object to build from.
     * @return The built LikeData object.
     */
    LikeData buildLikeData(boolean isPostLiked, Comment comment);

    /**
     * Builds a CommentData object from a Comment and a User.
     *
     * @param comment - The Comment object to build from.
     * @param user    - The User object to build from.
     * @return The built CommentData object.
     */
    CommentData buildCommentData(Comment comment, User user);
}
