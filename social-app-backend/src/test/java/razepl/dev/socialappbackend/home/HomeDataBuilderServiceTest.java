package razepl.dev.socialappbackend.home;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import razepl.dev.socialappbackend.entities.comment.Comment;
import razepl.dev.socialappbackend.entities.comment.CommentRepository;
import razepl.dev.socialappbackend.entities.commentlike.CommentLikeRepository;
import razepl.dev.socialappbackend.entities.friend.FriendsRepository;
import razepl.dev.socialappbackend.entities.post.Post;
import razepl.dev.socialappbackend.entities.postlike.PostLikeRepository;
import razepl.dev.socialappbackend.entities.user.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;
import razepl.dev.socialappbackend.home.data.CommentData;
import razepl.dev.socialappbackend.home.data.LikeData;
import razepl.dev.socialappbackend.home.data.PostData;
import razepl.dev.socialappbackend.home.data.UserData;
import razepl.dev.socialappbackend.home.interfaces.DataServiceInterface;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class HomeDataBuilderServiceTest {
    @Mock
    private PostLikeRepository postLikeRepository;

    @Mock
    private FriendsRepository friendsRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentLikeRepository commentLikeRepository;

    @Mock
    private DataServiceInterface dataServiceInterface;

    @InjectMocks
    private HomeDataBuilderService homeDataBuilderService;

    private User user;

    private Post post;

    private Comment comment;

    @BeforeEach
    final void init() {
        user = User
                .builder()
                .name("Jacek")
                .surname("Wasilewski")
                .email("jacek0@gmail.com")
                .password("AbcdDef123#!")
                .dateOfBirth(LocalDate.now())
                .role(Role.USER)
                .build();
        post = Post
                .builder()
                .postContent("Nothing important")
                .postDate(LocalDate.now())
                .user(user)
                .build();
        comment = Comment
                .builder()
                .post(post)
                .user(user)
                .commentDate(LocalDate.now())
                .commentContent("Well come on")
                .build();
    }

    @Test
    final void test_buildUserData_shouldReturnUserDataWithNonNullFields() {
        // given
        User user = new User();
        user.setLocation("New York");
        user.setGithub("github.com/user1");
        user.setLinkedin("linkedin.com/user1");
        user.setTwitter("twitter.com/user1");

        when(friendsRepository.countFriendByUser(any()))
                .thenReturn(10L);

        // when
        UserData result = homeDataBuilderService.buildUserData(user);

        // then
        assertEquals(user.getFullName(), result.fullName());
        assertEquals(user.getLocation(), result.location());
        assertEquals("", result.job());
        assertEquals(user.getGithub(), result.github());
        assertEquals(user.getLinkedin(), result.linkedin());
        assertEquals(user.getTwitter(), result.twitter());
        assertEquals(10L, result.numOfFriends());
    }

    @Test
    final void test_buildUserData_shouldReturnUserDataWithEmptyStringsForNullFields() {
        // given
        User user = new User();

        when(friendsRepository.countFriendByUser(any()))
                .thenReturn(10L);

        // when
        UserData result = homeDataBuilderService.buildUserData(user);

        // then
        assertEquals(user.getFullName(), result.fullName());
        assertEquals("", result.location());
        assertEquals("", result.job());
        assertEquals("", result.github());
        assertEquals("", result.linkedin());
        assertEquals("", result.twitter());
        assertEquals(10L, result.numOfFriends());
    }

    @Test
    final void test_buildPostData_shouldReturnPostDataWithGivenValues() {
        // given
        User user = new User();
        Post post = new Post(1L, "Hello world", LocalDate.now(), user);
        boolean isUserInFriends = true;
        boolean isPostLiked = false;

        when(postLikeRepository.countByPost(any()))
                .thenReturn(5L);

        when(commentRepository.countCommentsByPost(any()))
                .thenReturn(3L);

        // when
        PostData result = homeDataBuilderService.buildPostData(post, isUserInFriends, isPostLiked);

        // then
        assertEquals(post.getUser().getFullName(), result.postAuthor());
        assertEquals(post.getUser().getUsername(), result.username());
        assertEquals(post.getPostContent(), result.postContent());
        assertEquals(post.getPostDate(), result.postDate());
        assertEquals(isUserInFriends, result.isUserInFriends());
        assertEquals(3L, result.numOfComments());
        assertEquals(isPostLiked, result.isPostLiked());
        assertEquals(post.getPostId(), result.postId());
    }

    @Test
    final void test_buidLikeData_shouldReturnLikeDataWithGivenValuesForPost() {
        // given
        User user = new User();
        Post post = new Post(1L, "Hello world", LocalDate.now(), user);
        boolean isPostLiked = true;

        when(postLikeRepository.countByPost(any()))
                .thenReturn(5L);

        // when
        LikeData result = homeDataBuilderService.buidLikeData(isPostLiked, post);

        // then
        assertEquals(isPostLiked, result.isLiked());
        assertEquals(5L, result.numOfLikes());
    }

    @Test
    final void test_buildLikeData_shouldReturnLikeDataWithGivenValuesForComment() {
        // given
        User user = new User();
        Post post = new Post(1L, "Hello world", LocalDate.now(), user);
        Comment comment = new Comment(1L, user, post, "Nice post", LocalDate.now());
        boolean isCommentLiked = false;

        when(commentLikeRepository.countByComment(any()))
                .thenReturn(3L);

        // when
        LikeData result = homeDataBuilderService.buildLikeData(isCommentLiked, comment);

        // then
        assertEquals(isCommentLiked, result.isLiked());
        assertEquals(3L, result.numOfLikes());
    }

    @Test
    final void test_buildCommentData_shouldReturnCommentDataWithGivenValues() {
        // given
        User user = new User();
        Post post = new Post(1L, "Hello world", LocalDate.now(), user);
        Comment comment = new Comment(1L, user, post, "Nice post", LocalDate.now());
        boolean isCommentLiked = false;

        when(commentLikeRepository.countByComment(any()))
                .thenReturn(3L);

        // when
        CommentData result = homeDataBuilderService.buildCommentData(comment, user);

        // then
        assertEquals(comment.getUser().getFullName(), result.commentAuthor());
        assertEquals(comment.getCommentContent(), result.commentContent());
        assertEquals(comment.getCommentDate(), result.commentDate());
        assertEquals(comment.getCommentId(), result.commentId());
        assertEquals(isCommentLiked, result.isCommentLiked());
        assertEquals(3L, result.numOfLikes());
    }

    @Test
    final void test_buildUserData_null() {
        // given

        // when
        when(dataServiceInterface.buildUserData(null))
                .thenThrow(new NullArgumentException("Encountered null arguments in method!"));

        // then
        assertThrows(NullArgumentException.class, () -> dataServiceInterface.buildUserData(null));
    }

    @Test
    final void test_buildPostData_null() {
        // given

        // when
        when(dataServiceInterface.buildPostData(null, false, false))
                .thenThrow(new NullArgumentException("Encountered null arguments in method!"));

        // then
        assertThrows(NullArgumentException.class, () -> dataServiceInterface.buildPostData(null, false, false));
    }

    @Test
    final void test_buildLikeData_post_null() {
        // given

        // when
        when(dataServiceInterface.buidLikeData(false, null))
                .thenThrow(new NullArgumentException("Encountered null arguments in method!"));

        // then
        assertThrows(NullArgumentException.class, () -> dataServiceInterface.buidLikeData(false, null));
    }

    @Test
    final void test_buildLikeData_comment_null() {
        // given

        // when
        when(dataServiceInterface.buildLikeData(false, null))
                .thenThrow(new NullArgumentException("Encountered null arguments in method!"));

        // then
        assertThrows(NullArgumentException.class, () -> dataServiceInterface.buildLikeData(false, null));
    }

    @Test
    final void test_buildCommentData_null() {
        // given

        // when
        when(dataServiceInterface.buildCommentData(null, null))
                .thenThrow(new NullArgumentException("Encountered null arguments in method!"));

        // then
        assertThrows(NullArgumentException.class, () -> dataServiceInterface.buildCommentData(null, null));
    }

    @Test
    final void test_buildUserData_correct_usage() {
        // given
        UserData expected = UserData
                .builder()
                .fullName(user.getFullName())
                .twitter("")
                .github("")
                .linkedin("")
                .location("")
                .job("")
                .build();

        // when
        when(dataServiceInterface.buildUserData(user))
                .thenReturn(expected);

        UserData result = dataServiceInterface.buildUserData(user);

        // then
        assertEquals(expected, result);
    }

    @Test
    final void test_buildCommentData_correct_usage() {
        // given
        CommentData expected = CommentData
                .builder()
                .commentId(comment.getCommentId())
                .commentAuthor("Jacek Wasilewski")
                .isCommentLiked(false)
                .numOfLikes(0L)
                .commentDate(comment.getCommentDate())
                .commentContent("Well come on")
                .build();

        // when
        when(dataServiceInterface.buildCommentData(comment, user))
                .thenReturn(expected);

        CommentData result = dataServiceInterface.buildCommentData(comment, user);

        // then
        assertEquals(expected, result);
    }

    @Test
    final void test_buildPostData_correct_usage() {
        // given
        PostData expected = PostData
                .builder()
                .postId(post.getPostId())
                .postAuthor("Jacek Wasilewski")
                .username(user.getUsername())
                .postContent(post.getPostContent())
                .postDate(post.getPostDate())
                .numOfComments(1L)
                .build();

        // when
        when(dataServiceInterface.buildPostData(post, false, false))
                .thenReturn(expected);

        PostData result = dataServiceInterface.buildPostData(post, false, false);

        // then
        assertEquals(expected, result);
    }

    @Test
    final void test_buildLikeData_post_correct_usage() {
        // given
        LikeData expected = LikeData
                .builder()
                .isLiked(false)
                .numOfLikes(0L)
                .build();

        // when
        when(dataServiceInterface.buidLikeData(false, post)).thenReturn(expected);

        LikeData result = dataServiceInterface.buidLikeData(false, post);

        // then
        assertEquals(expected, result);
    }

    @Test
    final void test_buildLikeData_comment_correct_usage() {
        // given
        LikeData expected = LikeData
                .builder()
                .isLiked(false)
                .numOfLikes(0L)
                .build();

        // when
        when(dataServiceInterface.buildLikeData(false, comment)).thenReturn(expected);

        LikeData result = dataServiceInterface.buildLikeData(false, comment);

        // then
        assertEquals(expected, result);
    }
}
