package razepl.dev.socialappbackend.home;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.entities.friend.FriendsRepository;
import razepl.dev.socialappbackend.entities.like.LikeRepository;
import razepl.dev.socialappbackend.entities.post.Post;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.LikeData;
import razepl.dev.socialappbackend.home.data.PostData;
import razepl.dev.socialappbackend.home.data.UserData;
import razepl.dev.socialappbackend.home.interfaces.DataServiceInterface;

@Service
@RequiredArgsConstructor
public class HomeDataBuilderService implements DataServiceInterface {
    private final LikeRepository likeRepository;
    private final FriendsRepository friendsRepository;

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
                .numOfLikes(likeRepository.countByPost(post))
                .isPostLiked(isPostLiked)
                .postId(post.getPostId())
                .build();
    }

    @Override
    public final LikeData buidLikeData(boolean isPostLiked, Post post) {
        return LikeData
                .builder()
                .numOfLikes(likeRepository.countByPost(post))
                .isPostLiked(isPostLiked)
                .build();
    }

    private String convertNullIntoEmptyString(String value) {
        return value == null ? "" : value;
    }
}
