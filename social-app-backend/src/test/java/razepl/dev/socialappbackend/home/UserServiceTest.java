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
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private static User jacek;

    @BeforeEach
    void init() {
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
    void test_updateUsersJob_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> userService.updateUsersJob(null, jacek));
    }

    @Test
    void test_updateGithubData_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> userService.updateGithubData(null, jacek));
    }

    @Test
    void test_updateUsersLocation_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> userService.updateUsersLocation(null, jacek));
    }

    @Test
    void test_updateTwitterData_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> userService.updateTwitterData(null, jacek));
    }

    @Test
    void test_updateLinkedinData_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> userService.updateLinkedinData(null, jacek));
    }

    @Test
    void test_updateTwitterData_correct_usage() {
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
    void test_updateGithubData_correct_usage() {
        // given
        String expected = "myUrlData";

        // when
        userService.updateGithubData(expected, jacek);
        String result = userRepository.findByEmail(jacek.getEmail()).get().getGithub();

        // then
        assertEquals(expected, result);
    }

    @Test
    void test_updateLinkedinData_correct_usage() {
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
    void test_updateUsersLocation_correct_usage() {
        // given
        String expected = "myUrlData";

        // when
        userService.updateUsersLocation(expected, jacek);
        String result = userRepository.findByEmail(jacek.getEmail()).get().getLocation();

        // then
        assertEquals(expected, result);
    }

    @Test
    void test_updateUsersJob_correct_usage() {
        // given
        String expected = "myUrlData";

        // when
        userService.updateUsersJob(expected, jacek);
        String result = userRepository.findByEmail(jacek.getEmail()).get().getJob();

        // then
        assertEquals(expected, result);
    }
}
