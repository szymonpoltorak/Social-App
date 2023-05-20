package razepl.dev.socialappbackend.home;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.entities.comment.CommentRepository;
import razepl.dev.socialappbackend.entities.friend.Friend;
import razepl.dev.socialappbackend.entities.friend.FriendsRepository;
import razepl.dev.socialappbackend.exceptions.PostNotFoundException;
import razepl.dev.socialappbackend.home.data.*;
import razepl.dev.socialappbackend.home.interfaces.DataServiceInterface;
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

/**
 * Service class for /api/home controller.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HomeService implements HomeServiceInterface {
    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final DataServiceInterface dataServiceInterface;

    @Override
    public final UserData buildUserDataFromDb(User authUser) {
        User user = userRepository.findByEmail(authUser.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("User was not found in database!")
        );

        log.info("Building data for user : {}", user);

        return dataServiceInterface.buildUserData(user);
    }

    @Override
    public final List<FriendData> buildUsersFriendList(User authUser) {
        User user = userRepository.findByEmail(authUser.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("User does not exist!")
        );
        Page<Friend> friendList = friendsRepository.findFriendsByUser(user, Pageable.ofSize(12)).orElseThrow();

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
                .map(post -> dataServiceInterface.buildPostData(
                        post,
                        friendsRepository.findByFriendUsernameAndUser(post.getUser().getUsername(), user).isPresent(),
                        likeRepository.findByUserAndPost(user, post).isPresent()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public final PostData createNewPost(String postContent, User user) {
        @Valid Post post = Post
                .builder()
                .postDate(LocalDate.now())
                .postContent(postContent)
                .numOfLikes(0L)
                .user(user)
                .build();
        log.info("Newly created post : {}", post);

        postRepository.save(post);

        return dataServiceInterface.buildPostData(post, false, false);
    }

    @Override
    public final LikeData updatePostLikeCounter(long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post does not exist!")
        );
        log.info("Post from repository : {}", post);
        log.info("User from repository : {}", user);

        Optional<Like> like = likeRepository.findByUserAndPost(user, post);

        if (like.isPresent()) {
            likeRepository.delete(like.get());

            log.info("Like i got : {}", like.get());

            return dataServiceInterface.buidLikeData(false, post);
        }
        Like newLike = Like
                .builder()
                .post(post)
                .user(user)
                .build();
        log.info("New like to db : {}", newLike);

        likeRepository.save(newLike);

        return dataServiceInterface.buidLikeData(true, post);
    }

    @Override
    public final void deletePostByPostId(long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public final List<CommentData> getListOfComments(long postId) {
        return null;
    }

    @Override
    public final CommentData createComment(long postId, User user) {
        return null;
    }
}
