package razepl.dev.socialappbackend.home;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.friend.Friend;
import razepl.dev.socialappbackend.friend.FriendsRepository;
import razepl.dev.socialappbackend.home.data.*;
import razepl.dev.socialappbackend.home.interfaces.HomeServiceInterface;
import razepl.dev.socialappbackend.post.Post;
import razepl.dev.socialappbackend.post.PostRepository;
import razepl.dev.socialappbackend.user.User;
import razepl.dev.socialappbackend.user.interfaces.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeService implements HomeServiceInterface {
    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;
    private final PostRepository postRepository;

    @Override
    public final UserData buildUserDataFromDb(String username) {
        User user = userRepository.findByEmail(username).orElseThrow();

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
    public final List<FriendData> buildUsersFriendList(String username) {
        User user = userRepository.findByEmail(username).orElseThrow();
        List<Friend> friendList = friendsRepository.findAllByUser(user).orElseThrow();

        log.info("Friend list for user : {}", user);

        return friendList
                .stream()
                .map(Friend::buildData)
                .collect(Collectors.toList());
    }

    @Override
    public final List<PostData> getTheListOfPostsByNumberOfSite(int numOfSite) {
        if (numOfSite < 0) {
            throw new IllegalArgumentException("Num of site cannot be less than 0");
        }
        Page<Post> postList = postRepository.getPosts(Pageable.ofSize(100).withPage(numOfSite));

        log.info("Number of posts that has been taken: {}", postList.getNumberOfElements());

        return postList
                .stream()
                .map(Post::buildData)
                .collect(Collectors.toList());
    }

    @Override
    public final PostData createNewPost(PostRequest request) {
        User user = userRepository.findByEmail(request.authorUsername()).orElseThrow();

        @Valid Post post = Post
                .builder()
                .postDate(LocalDate.now())
                .postContent(request.postContent())
                .numOfLikes(0L)
                .user(user)
                .build();
        log.info("Newly created post : {}", post);

        postRepository.save(post);

        return post.buildData();
    }

    @Override
    public final DataResponse updatePostLikeCounter(LikeRequest request) {
        Post post = postRepository.findById(request.postId()).orElseThrow();

        log.info("Post from repository : {}", post);

        post.setNumOfLikes(request.numOfLikes());

        postRepository.save(post);

        return new DataResponse("Successfully incremented like counter!");
    }

    @Override
    public final void deletePostByPostId(long postId) {
        postRepository.deleteById(postId);
    }

    private String convertNullIntoEmptyString(String value) {
        return value == null ? "" : value;
    }
}
