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
import razepl.dev.socialappbackend.exceptions.NullArgumentException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private static User jacek;

    @BeforeEach
    final void init() {
        jacek = User
                .builder()
                .name("Jacek")
                .surname("Wasilewski")
                .email("jacek0@gmail.com")
                .password("AbcdDef123#!")
                .dateOfBirth(LocalDate.now())
                .role(Role.USER)
                .build();
    }

    @Test
    final void test_updateUsersJob_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> userService.updateUsersJob(null, jacek));
    }

    @Test
    final void test_updateGithubData_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> userService.updateGithubData(null, jacek));
    }

    @Test
    final void test_updateUsersLocation_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> userService.updateUsersLocation(null, jacek));
    }

    @Test
    final void test_updateTwitterData_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> userService.updateTwitterData(null, jacek));
    }

    @Test
    final void test_updateLinkedinData_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> userService.updateLinkedinData(null, jacek));
    }

    @Test
    final void test_updateTwitterData_correct_usage() {
        // given
        String expected = "myUrlData";

        userRepository.save(jacek);

        // when
        userService.updateTwitterData(expected, jacek);
        String result = userRepository.findByEmail(jacek.getEmail()).get().getTwitter();

        // then
        assertEquals(expected, result);
    }

    @Test
    final void test_updateGithubData_correct_usage() {
        // given
        String expected = "myUrlData";

        // when
        userService.updateGithubData(expected, jacek);
        String result = userRepository.findByEmail(jacek.getEmail()).get().getGithub();

        // then
        assertEquals(expected, result);
    }

    @Test
    final void test_updateLinkedinData_correct_usage() {
        // given
        String expected = "myUrlData";

        userRepository.save(jacek);

        // when
        userService.updateLinkedinData(expected, jacek);
        String result = userRepository.findByEmail(jacek.getEmail()).get().getLinkedin();

        // then
        assertEquals(expected, result);
    }

    @Test
    final void test_updateTwitterData_blank_string() {
        // given
        String value = "  ";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.updateLinkedinData(value, jacek));
    }

    @Test
    final void test_updateGithubData_blank_string() {
        // given
        String value = "  ";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.updateLinkedinData(value, jacek));
    }

    @Test
    final void test_updateLinkedinData_blank_string() {
        // given
        String value = "  ";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.updateLinkedinData(value, jacek));
    }

    @Test
    final void test_updateUsersLocation_blank_string() {
        // given
        String value = "  ";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.updateLinkedinData(value, jacek));
    }

    @Test
    final void test_updateUsersJob_blank_string() {
        // given
        String value = "  ";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.updateLinkedinData(value, jacek));
    }

    @Test
    final void test_updateUsersLocation_correct_usage() {
        // given
        String expected = "myUrlData";

        // when
        userService.updateUsersLocation(expected, jacek);
        String result = userRepository.findByEmail(jacek.getEmail()).get().getLocation();

        // then
        assertEquals(expected, result);
    }

    @Test
    final void test_updateUsersJob_correct_usage() {
        // given
        String expected = "myUrlData";

        // when
        userService.updateUsersJob(expected, jacek);
        String result = userRepository.findByEmail(jacek.getEmail()).get().getJob();

        // then
        assertEquals(expected, result);
    }

    @Test
    final void test_updateTwitterData_empty_string() {
        // given
        String value = "";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.updateLinkedinData(value, jacek));
    }

    @Test
    final void test_updateGithubData_empty_string() {
        // given
        String value = "";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.updateLinkedinData(value, jacek));
    }

    @Test
    final void test_updateLinkedinData_empty_string() {
        // given
        String value = "";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.updateLinkedinData(value, jacek));
    }

    @Test
    final void test_updateUsersLocation_empty_string() {
        // given
        String value = "";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.updateLinkedinData(value, jacek));
    }

    @Test
    final void test_updateUsersJob_empty_string() {
        // given
        String value = "";

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.updateLinkedinData(value, jacek));
    }
}
