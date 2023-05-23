package razepl.dev.socialappbackend.home;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import razepl.dev.socialappbackend.entities.user.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.exceptions.NegativeIdException;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;
import razepl.dev.socialappbackend.home.interfaces.HomeServiceInterface;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class HomeServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HomeServiceInterface homeService;

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
