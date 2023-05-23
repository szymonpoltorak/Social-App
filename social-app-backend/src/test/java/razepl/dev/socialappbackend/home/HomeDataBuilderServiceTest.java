package razepl.dev.socialappbackend.home;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import razepl.dev.socialappbackend.entities.comment.Comment;
import razepl.dev.socialappbackend.entities.comment.CommentRepository;
import razepl.dev.socialappbackend.entities.post.Post;
import razepl.dev.socialappbackend.entities.post.PostRepository;
import razepl.dev.socialappbackend.entities.user.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;
import razepl.dev.socialappbackend.home.data.CommentData;
import razepl.dev.socialappbackend.home.data.LikeData;
import razepl.dev.socialappbackend.home.data.PostData;
import razepl.dev.socialappbackend.home.data.UserData;
import razepl.dev.socialappbackend.home.interfaces.DataServiceInterface;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class HomeDataBuilderServiceTest {
    @Autowired
    private DataServiceInterface dataServiceInterface;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

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
        userRepository.save(user);
        postRepository.save(post);
        commentRepository.save(comment);
    }

    @Test
    final void test_buildUserData_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> dataServiceInterface.buildUserData(null));
    }

    @Test
    final void test_buildPostData_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> dataServiceInterface.buildPostData(null, false, false));
    }

    @Test
    final void test_buildLikeData_post_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> dataServiceInterface.buidLikeData(false, null));
    }

    @Test
    final void test_buildLikeData_comment_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> dataServiceInterface.buildLikeData(false, null));
    }

    @Test
    final void test_buildCommentData_null() {
        // given

        // when

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
        LikeData result = dataServiceInterface.buildLikeData(false, comment);

        // then
        assertEquals(expected, result);
    }
}
