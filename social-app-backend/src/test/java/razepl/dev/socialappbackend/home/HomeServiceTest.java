package razepl.dev.socialappbackend.home;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
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
import razepl.dev.socialappbackend.entities.user.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.exceptions.NegativeIdException;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;
import razepl.dev.socialappbackend.home.data.*;
import razepl.dev.socialappbackend.home.interfaces.DataServiceInterface;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class HomeServiceTest {
    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentLikeRepository commentLikeRepository;

    @Mock
    private DataServiceInterface dataServiceInterface;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostLikeRepository postLikeRepository;

    @Mock
    private FriendsRepository friendRepository;

    @InjectMocks
    private HomeService homeService;

    private User user;

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
        userRepository.save(user);
    }

    @Test
    final void test_updateCommentLikeCounter_shouldCreateNewLikeIfNotPresent() {
        // given
        User user = new User();
        Post post = new Post(1L, "Hello world", LocalDate.now(), user);
        Comment comment = new Comment(1L, user, post, "Nice post", LocalDate.now());

        when(commentRepository.findById(any()))
                .thenReturn(Optional.of(comment));

        when(commentLikeRepository.findByUserAndComment(any(), any()))
                .thenReturn(Optional.empty());

        when(dataServiceInterface.buildLikeData(anyBoolean(), any()))
                .thenReturn(new LikeData(1L, true));

        // when
        LikeData result = homeService.updateCommentLikeCounter(1L, user);

        // then
        assertTrue(result.isLiked());
        assertEquals(1L, result.numOfLikes());
    }

    @Test
    final void test_updateCommentLikeCounter_shouldDeleteExistingLikeIfPresent() {
        // given
        User user = new User();
        Post post = new Post(1L, "Hello world", LocalDate.now(), user);
        Comment comment = new Comment(1L, user, post, "Nice post", LocalDate.now());
        CommentLike like = new CommentLike(1L, comment, user);

        when(commentRepository.findById(any()))
                .thenReturn(Optional.of(comment));

        when(commentLikeRepository.findByUserAndComment(any(), any()))
                .thenReturn(Optional.of(like));

        when(dataServiceInterface.buildLikeData(anyBoolean(), any()))
                .thenReturn(new LikeData(0L, false));

        // when
        LikeData result = homeService.updateCommentLikeCounter(1L, user);

        // then
        assertFalse(result.isLiked());
        assertEquals(0L, result.numOfLikes());
    }

    @Test
    final void test_updatePostLikeCounter_shouldCreateNewLikeIfNotPresent() {
        // given
        User user = new User();
        Post post = new Post(1L, "Hello world", LocalDate.now(), user);

        when(postRepository.findById(any()))
                .thenReturn(Optional.of(post));

        when(postLikeRepository.findByUserAndPost(any(), any()))
                .thenReturn(Optional.empty());

        when(dataServiceInterface.buidLikeData(anyBoolean(), any()))
                .thenReturn(new LikeData(1L, true));

        // when
        LikeData result = homeService.updatePostLikeCounter(1L, user);

        // then
        assertTrue(result.isLiked());
        assertEquals(1L, result.numOfLikes());
    }

    @Test
    final void test_updatePostLikeCounter_shouldDeleteExistingLikeIfPresent() {
        // given
        User user = new User();
        Post post = new Post(1L, "Hello world", LocalDate.now(), user);
        PostLike like = new PostLike(1L, post, user);

        when(postRepository.findById(any()))
                .thenReturn(Optional.of(post));

        when(postLikeRepository.findByUserAndPost(any(), any()))
                .thenReturn(Optional.of(like));

        when(dataServiceInterface.buidLikeData(anyBoolean(), any()))
                .thenReturn(new LikeData(0L, false));

        // when
        LikeData result = homeService.updatePostLikeCounter(1L, user);

        // then
        assertFalse(result.isLiked());
        assertEquals(0L, result.numOfLikes());
    }

    @Test
    final void test_getListOfComments_shouldReturnListOfCommentData() {
        // given
        long postId = 1L;
        int numOfSite = 0;
        User user = new User();
        Post post = new Post(1L, "Hello world", LocalDate.now(), user);
        Comment comment1 = new Comment(1L, user, post, "Nice post", LocalDate.now());
        Comment comment2 = new Comment(2L, user, post, "I agree", LocalDate.now());

        when(commentRepository.findCommentsByPostId(anyLong(), any()))
                .thenReturn(new PageImpl<>(List.of(comment1, comment2)));

        when(dataServiceInterface.buildCommentData(any(), any()))
                .thenReturn(CommentData
                        .builder()
                        .commentContent("Nice post")
                        .commentDate(LocalDate.now())
                        .isCommentLiked(false)
                        .commentId(1L)
                        .build()
                )
                .thenReturn(CommentData
                        .builder()
                        .commentContent("I agree")
                        .commentDate(LocalDate.now())
                        .isCommentLiked(false)
                        .commentId(2L)
                        .build()
                );

        // when
        List<CommentData> result = homeService.getListOfComments(postId, numOfSite, user);

        // then
        assertEquals(2, result.size());
        assertEquals(comment1.getCommentId(), result.get(0).commentId());
        assertEquals(comment1.getCommentContent(), result.get(0).commentContent());
        assertEquals(comment1.getCommentDate(), result.get(0).commentDate());
        assertFalse(result.get(0).isCommentLiked());
        assertEquals(comment2.getCommentId(), result.get(1).commentId());
        assertEquals(comment2.getCommentContent(), result.get(1).commentContent());
        assertEquals(comment2.getCommentDate(), result.get(1).commentDate());
        assertFalse(result.get(1).isCommentLiked());
    }

    @Test
    final void test_createComment_shouldCreateNewCommentAndReturnCommentData() {
        // given
        User user = new User();
        Post post = new Post(1L, "Hello world", LocalDate.now(), user);
        CommentRequest request = new CommentRequest("Nice post", 1L);

        when(postRepository.findById(any()))
                .thenReturn(Optional.of(post));

        when(dataServiceInterface.buildCommentData(any(), any()))
                .thenReturn(CommentData
                        .builder()
                        .commentContent("Nice post")
                        .commentDate(LocalDate.now())
                        .isCommentLiked(false)
                        .build()
                );

        // when
        CommentData result = homeService.createComment(request, user);

        // then
        assertEquals(request.commentContent(), result.commentContent());
        assertEquals(LocalDate.now(), result.commentDate());
        assertFalse(result.isCommentLiked());
    }

    @Test
    final void test_buildUserDataFromDb_shouldReturnUserDataIfExists() {
        // given
        User user = new User();
        UserData userData = UserData.builder().build();

        when(userRepository.findByEmail(any()))
                .thenReturn(java.util.Optional.of(user));

        when(dataServiceInterface.buildUserData(any()))
                .thenReturn(userData);

        // when
        UserData result = homeService.buildUserDataFromDb(user);

        // then
        assertEquals(userData, result);
    }

    @Test
    final void test_buildUsersFriendList_shouldReturnListOfFriendData() {
        // given
        User user = User
                .builder()
                .email("user1@gmail.com")
                .build();
        User friend1 = User
                .builder()
                .email("user2@gmail.com")
                .build();
        User friend2 = User
                .builder()
                .email("user3@gmail.com")
                .build();
        Friend friendData1 = Friend
                .builder()
                .user(user)
                .friendUsername(friend1.getUsername())
                .build();
        Friend friendData2 = Friend
                .builder()
                .user(user)
                .friendUsername(friend2.getUsername())
                .build();

        when(userRepository.findByEmail(any()))
                .thenReturn(java.util.Optional.of(user));

        when(friendRepository.findFriendsByUser(any(), any()))
                .thenReturn(new PageImpl<>(List.of(friendData1, friendData2)));

        // when
        List<FriendData> result = homeService.buildUsersFriendList(user);

        // then
        assertEquals(2, result.size());
        assertEquals(friendData1.buildData(), result.get(0));
        assertEquals(friendData2.buildData(), result.get(1));
    }

    @Test
    final void test_getTheListOfPostsByNumberOfSite_shouldReturnListOfPostData() {
        // given
        int numOfSite = 0;
        User user = new User();
        Post post1 = new Post(1L, "Hello world", LocalDate.now(), user);
        Post post2 = new Post(2L, "Goodbye world", LocalDate.now(), user);
        PostData postData1 = PostData
                .builder()
                .postDate(LocalDate.now())
                .postContent("Hello world")
                .postId(1L)
                .username(user.getUsername())
                .build();
        PostData postData2 = PostData
                .builder()
                .postDate(LocalDate.now())
                .postContent("Goodbye world")
                .postId(2L)
                .username(user.getUsername())
                .build();

        when(postRepository.getPosts(any()))
                .thenReturn(new PageImpl<>(List.of(post1, post2)));

        when(dataServiceInterface.buildPostData(any(), anyBoolean(), anyBoolean()))
                .thenReturn(postData1)
                .thenReturn(postData2);

        // when
        List<PostData> result = homeService.getTheListOfPostsByNumberOfSite(numOfSite, user);

        // then
        assertEquals(2, result.size());
        assertEquals(postData1, result.get(0));
        assertEquals(postData2, result.get(1));
    }

    @Test
    final void test_createNewPost_shouldCreateNewPostAndReturnPostData() {
        // given
        String postContent = "Hello world";
        User user = new User();
        PostData postData = PostData
                .builder()
                .postDate(LocalDate.now())
                .postContent("Hello world")
                .postId(1L)
                .username(user.getUsername())
                .build();

        when(dataServiceInterface.buildPostData(any(), anyBoolean(), anyBoolean()))
                .thenReturn(postData);

        // when
        PostData result = homeService.createNewPost(postContent, user);

        // then
        assertEquals(postData, result);
    }

    @Test
    final void test_buildUserDataFromDb_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> homeService.buildUserDataFromDb(null));
    }

    @Test
    final void test_buildUsersFriendList_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> homeService.buildUsersFriendList(null));
    }

    @Test
    final void test_getTheListOfPostsByNumberOfSite_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> homeService.getTheListOfPostsByNumberOfSite(0, null));
    }

    @Test
    final void test_getTheListOfPostsByNumberOfSite_negative_numOfSite() {
        // given

        // when

        // then
        assertThrows(NegativeIdException.class, () -> homeService.getTheListOfPostsByNumberOfSite(-1, user));
    }

    @Test
    final void test_createNewPost_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> homeService.createNewPost(null, null));
    }

    @Test
    final void test_updatePostLikeCounter_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> homeService.updatePostLikeCounter(0L, null));
    }

    @Test
    final void test_updatePostLikeCounter_negative_id() {
        // given

        // when

        // then
        assertThrows(NegativeIdException.class, () -> homeService.updatePostLikeCounter(-1L, user));
    }

    @Test
    final void test_deletePostByPostId_negative_id() {
        // given

        // when

        // then
        assertThrows(NegativeIdException.class, () -> homeService.deletePostByPostId(-1L));
    }

    @Test
    final void test_getListOfComments_negative_id() {
        // given

        // when

        // then
        assertThrows(NegativeIdException.class, () -> homeService.getListOfComments(-1L, 0, user));
    }

    @Test
    final void test_getListOfComments_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> homeService.getListOfComments(0L, 0, null));
    }

    @Test
    final void test_getListOfComments_negative_site() {
        // given

        // when

        // then
        assertThrows(NegativeIdException.class, () -> homeService.getListOfComments(0L, -1, user));
    }

    @Test
    final void test_createComment_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> homeService.createComment(null, user));
    }

    @Test
    final void test_updateCommentLikeCounter_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> homeService.updateCommentLikeCounter(0L, null));
    }

    @Test
    final void test_updateCommentLikeCounter_negative_id() {
        // given

        // when

        // then
        assertThrows(NegativeIdException.class, () -> homeService.updateCommentLikeCounter(-1L, user));
    }
}
