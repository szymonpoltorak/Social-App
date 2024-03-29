package razepl.dev.socialappbackend.home;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.entities.comment.Comment;
import razepl.dev.socialappbackend.entities.comment.CommentRepository;
import razepl.dev.socialappbackend.entities.commentlike.CommentLike;
import razepl.dev.socialappbackend.entities.commentlike.CommentLikeRepository;
import razepl.dev.socialappbackend.entities.friend.Friend;
import razepl.dev.socialappbackend.entities.friend.FriendsRepository;
import razepl.dev.socialappbackend.entities.post.Post;
import razepl.dev.socialappbackend.entities.post.PostRepository;
import razepl.dev.socialappbackend.entities.postlike.PostLike;
import razepl.dev.socialappbackend.entities.postlike.PostLikeRepository;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.exceptions.CommentNotFoundException;
import razepl.dev.socialappbackend.exceptions.PostNotFoundException;
import razepl.dev.socialappbackend.home.data.CommentRequest;
import razepl.dev.socialappbackend.home.data.CommentResponse;
import razepl.dev.socialappbackend.home.data.FriendResponse;
import razepl.dev.socialappbackend.home.data.LikeResponse;
import razepl.dev.socialappbackend.home.data.PostResponse;
import razepl.dev.socialappbackend.home.data.UserResponse;
import razepl.dev.socialappbackend.home.interfaces.HomeDataBuilderService;
import razepl.dev.socialappbackend.home.interfaces.HomeService;
import razepl.dev.socialappbackend.validators.ArgumentValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static razepl.dev.socialappbackend.home.constants.PageSizes.COMMENT_LIST_SIZE;
import static razepl.dev.socialappbackend.home.constants.PageSizes.FRIEND_LIST_PAGE;
import static razepl.dev.socialappbackend.home.constants.PageSizes.POST_LIST_PAGE;

/**
 * Service class for /api/home controller.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final UserRepository userRepository;
    private final FriendsRepository friendsRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentRepository commentRepository;
    private final HomeDataBuilderService dataServiceInterface;
    private final CommentLikeRepository commentLikeRepository;

    @Override
    public final UserResponse buildUserDataFromDb(User authUser) {
        ArgumentValidator.throwIfNull(authUser);

        log.info("Getting userdata for user : {}", authUser);

        User user = userRepository.findByEmail(authUser.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("User was not found in database!")
        );

        log.info("Building data for user : {}", user);

        return dataServiceInterface.buildUserData(user);
    }

    @Override
    public final List<FriendResponse> buildUsersFriendList(User authUser) {
        ArgumentValidator.throwIfNull(authUser);

        log.info("Finding list of users for : {}", authUser);

        User user = userRepository.findByEmail(authUser.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("User does not exist!")
        );
        Page<Friend> friendList = friendsRepository.findFriendsByUser(user, Pageable.ofSize(FRIEND_LIST_PAGE));

        log.info("Friend list for user : {}", user);

        return friendList
                .stream()
                .map(Friend::buildData)
                .toList();
    }

    @Override
    public final List<PostResponse> getTheListOfPostsByNumberOfSite(int numOfSite, User user) {
        ArgumentValidator.throwIfNull(user);
        ArgumentValidator.throwIfNegativeId(numOfSite);

        log.info("Number of site for posts : {}", numOfSite);
        log.info("User that sent request: {}", user);

        if (numOfSite < 0) {
            throw new IllegalArgumentException("Num of site cannot be less than 0");
        }
        Page<Post> postList = postRepository.getPosts(Pageable.ofSize(POST_LIST_PAGE).withPage(numOfSite));

        log.info("Number of posts that has been taken: {}", postList.getNumberOfElements());

        return postList
                .stream()
                .map(post -> dataServiceInterface.buildPostData(
                        post,
                        friendsRepository.findByFriendUsernameAndUser(post.getUser().getUsername(), user).isPresent(),
                        postLikeRepository.findByUserAndPost(user, post).isPresent()
                ))
                .toList();
    }

    @Override
    public final PostResponse createNewPost(String postContent, User user) {
        ArgumentValidator.throwIfNull(postContent, user);

        log.info("Creating post with data : {}", postContent);
        log.info("User who wants to create post : {}", user);

        @Valid Post post = Post
                .builder()
                .postDate(LocalDate.now())
                .postContent(postContent)
                .user(user)
                .build();
        log.info("Newly created post : {}", post);

        postRepository.save(post);

        return dataServiceInterface.buildPostData(post, false, false);
    }

    @Override
    public final LikeResponse updatePostLikeCounter(long postId, User user) {
        ArgumentValidator.throwIfNull(user);
        ArgumentValidator.throwIfNegativeId(postId);

        log.info("User wants to change number of like with data : {}", postId);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post does not exist!")
        );
        log.info("Post from repository : {}", post);
        log.info("User from repository : {}", user);

        Optional<PostLike> like = postLikeRepository.findByUserAndPost(user, post);

        if (like.isPresent()) {
            postLikeRepository.delete(like.get());

            log.info("Like i got : {}", like.get());

            return dataServiceInterface.buidLikeData(false, post);
        }
        PostLike newLike = PostLike
                .builder()
                .post(post)
                .user(user)
                .build();
        log.info("New like to db : {}", newLike);

        postLikeRepository.save(newLike);

        return dataServiceInterface.buidLikeData(true, post);
    }

    @Override
    public final void deletePostByPostId(long postId) {
        ArgumentValidator.throwIfNegativeId(postId);

        log.info("Removing post of id : {}", postId);

        postRepository.deleteById(postId);
    }

    @Override
    public final List<CommentResponse> getListOfComments(long postId, int numOfSite, User user) {
        ArgumentValidator.throwIfNull(user);
        ArgumentValidator.throwIfNegativeId(postId);
        ArgumentValidator.throwIfNegativeId(numOfSite);

        log.info("Get list of comments for post of id: {}", postId);
        log.info("Num Of Site : {}", numOfSite);

        Pageable pageable = Pageable.ofSize(COMMENT_LIST_SIZE).withPage(numOfSite);
        Page<Comment> commentList = commentRepository.findCommentsByPostId(postId, pageable);

        log.info("Returning {} comments", commentList.getNumberOfElements());

        return commentList
                .stream()
                .map(comment -> dataServiceInterface.buildCommentData(comment, user))
                .toList();
    }

    @Override
    public final CommentResponse createComment(CommentRequest request, User user) {
        ArgumentValidator.throwIfNull(request, user);

        log.info("Creating comment with data: {}\nOf User: {}", request, user);

        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new PostNotFoundException("Post does not exist!"));

        @Valid Comment comment = Comment
                .builder()
                .post(post)
                .user(user)
                .commentContent(request.commentContent())
                .commentDate(LocalDate.now())
                .build();
        log.info("Saving comment : {}", comment);

        commentRepository.save(comment);

        return dataServiceInterface.buildCommentData(comment, user);
    }

    @Override
    public final LikeResponse updateCommentLikeCounter(long commentId, User user) {
        ArgumentValidator.throwIfNull(user);
        ArgumentValidator.throwIfNegativeId(commentId);

        log.info("Creating like for comment of id : {}\nBy User : {}", commentId, user);

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CommentNotFoundException("Comment does not exist!")
        );
        log.info("Post from repository : {}", comment);
        log.info("User from repository : {}", user);

        Optional<CommentLike> like = commentLikeRepository.findByUserAndComment(user, comment);

        if (like.isPresent()) {
            commentLikeRepository.delete(like.get());

            log.info("Like i got : {}", like.get());

            return dataServiceInterface.buildLikeData(false, comment);
        }
        CommentLike newLike = CommentLike
                .builder()
                .comment(comment)
                .user(user)
                .build();
        log.info("New like to db : {}", newLike);

        commentLikeRepository.save(newLike);

        return dataServiceInterface.buildLikeData(true, comment);
    }
}
