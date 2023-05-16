package razepl.dev.socialappbackend.home;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.entities.friend.Friend;
import razepl.dev.socialappbackend.entities.friend.FriendsRepository;
import razepl.dev.socialappbackend.home.data.*;
import razepl.dev.socialappbackend.home.interfaces.HomeServiceInterface;
import razepl.dev.socialappbackend.entities.like.Like;
import razepl.dev.socialappbackend.entities.like.LikeRepository;
import razepl.dev.socialappbackend.entities.post.Post;
import razepl.dev.socialappbackend.entities.post.PostRepository;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeService implements HomeServiceInterface {
    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    @Override
    public final UserData buildUserDataFromDb(User authUser) {
        User user = userRepository.findByEmail(authUser.getEmail()).orElseThrow();

        log.info("Building data for user : {}", user);

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
    public final List<FriendData> buildUsersFriendList(User authUser) {
        User user = userRepository.findByEmail(authUser.getEmail()).orElseThrow();
        List<Friend> friendList = friendsRepository.findAllByUser(user).orElseThrow();

        log.info("Friend list for user : {}", user);

        return friendList
                .stream()
                .map(Friend::buildData)
                .collect(Collectors.toList());
    }

    @Override
    public final List<PostData> getTheListOfPostsByNumberOfSite(int numOfSite, User user) {
        if (numOfSite < 0) {
            throw new IllegalArgumentException("Num of site cannot be less than 0");
        }
        Page<Post> postList = postRepository.getPosts(Pageable.ofSize(100).withPage(numOfSite));

        log.info("Number of posts that has been taken: {}", postList.getNumberOfElements());

        return postList
                .stream()
                .map(post -> PostData
                        .builder()
                        .postAuthor(post.getUser().getFullName())
                        .username(post.getUser().getUsername())
                        .postContent(post.getPostContent())
                        .postDate(post.getPostDate())
                        .numOfLikes(likeRepository.countByPost(post))
                        .isPostLiked(likeRepository.findByUserAndPost(user, post).isPresent())
                        .postId(post.getPostId())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public final PostData createNewPost(PostRequest request, User user) {
        @Valid Post post = Post
                .builder()
                .postDate(LocalDate.now())
                .postContent(request.postContent())
                .numOfLikes(0L)
                .user(user)
                .build();
        log.info("Newly created post : {}", post);

        postRepository.save(post);

        return PostData
                .builder()
                .postAuthor(post.getUser().getFullName())
                .username(post.getUser().getUsername())
                .postContent(post.getPostContent())
                .postDate(post.getPostDate())
                .isPostLiked(false)
                .numOfLikes(likeRepository.countByPost(post))
                .postId(post.getPostId())
                .build();
    }

    @Override
    public final LikeResponse updatePostLikeCounter(LikeRequest request, User user) {
        Post post = postRepository.findById(request.postId()).orElseThrow();

        log.info("Post from repository : {}", post);
        log.info("User from repository : {}", user);

        Optional<Like> like = likeRepository.findByUserAndPost(user, post);

        if (like.isPresent()) {
            likeRepository.delete(like.get());

            log.info("Like i got : {}", like.get());

            return LikeResponse
                    .builder()
                    .numOfLikes(likeRepository.countByPost(post))
                    .isPostLiked(false)
                    .build();
        }
        Like newLike = Like
                .builder()
                .post(post)
                .user(user)
                .build();
        log.info("New like to db : {}", newLike);

        likeRepository.save(newLike);

        return LikeResponse
                .builder()
                .isPostLiked(true)
                .numOfLikes(likeRepository.countByPost(post))
                .build();
    }

    @Override
    public final void deletePostByPostId(long postId) {
        postRepository.deleteById(postId);
    }

    private String convertNullIntoEmptyString(String value) {
        return value == null ? "" : value;
    }
}
