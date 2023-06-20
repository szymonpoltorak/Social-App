package razepl.dev.socialappbackend.home;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import razepl.dev.socialappbackend.entities.friend.Friend;
import razepl.dev.socialappbackend.entities.friend.FriendsRepository;
import razepl.dev.socialappbackend.entities.user.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.exceptions.FriendNotFoundException;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;
import razepl.dev.socialappbackend.exceptions.UsersAlreadyFriendsException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {
//    @Autowired
//    private UserService userService;

//    @Autowired
//    private UserRepository userRepository;

    private static User jacek;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FriendsRepository friendsRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    private User friend;

    private Friend friendEntity;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .role(Role.USER)
                .password("hashedPassword")
                .build();

        jacek = User
                .builder()
                .name("Jacek")
                .surname("Wasilewski")
                .email("jacek0@gmail.com")
                .password("AbcdDef123#!")
                .dateOfBirth(LocalDate.now())
                .role(Role.USER)
                .build();

        friend = User.builder()
                .name("Jane")
                .surname("Doe")
                .email("jane.doe@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .role(Role.USER)
                .password("hashedPassword")
                .build();

        friendEntity = Friend
                .builder()
                .friendName(friend.getFullName())
                .friendUsername(friend.getUsername())
                .friendJob(friend.getJob())
                .user(user)
                .build();
    }

    @Test
    void updateTwitterData_should_update_user_and_save_it() {
        // given
        String updateData = "john_doe";

        // when
        userService.updateTwitterData(updateData, user);

        // then
        assertEquals(updateData, user.getTwitter());
        verify(userRepository).save(user);
    }

    @Test
    void updateTwitterData_should_throw_exception_if_update_data_is_null() {
        // given
        String updateData = null;

        // when and then
        assertThrows(IllegalArgumentException.class, () -> userService.updateTwitterData(updateData, user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateTwitterData_should_throw_exception_if_update_data_is_empty() {
        // given
        String updateData = "";

        // when and then
        assertThrows(IllegalArgumentException.class, () -> userService.updateTwitterData(updateData, user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateLinkedinData_should_update_user_and_save_it() {
        // given
        String updateData = "john-doe";

        // when
        userService.updateLinkedinData(updateData, user);

        // then
        assertEquals(updateData, user.getLinkedin());
        verify(userRepository).save(user);
    }

    @Test
    void updateLinkedinData_should_throw_exception_if_update_data_is_null() {
        // given
        String updateData = null;

        // when and then
        assertThrows(IllegalArgumentException.class, () -> userService.updateLinkedinData(updateData, user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateLinkedinData_should_throw_exception_if_update_data_is_empty() {
        // given
        String updateData = "";

        // when and then
        assertThrows(IllegalArgumentException.class, () -> userService.updateLinkedinData(updateData, user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateGithubData_should_update_user_and_save_it() {
        // given
        String updateData = "johndoe";

        // when
        userService.updateGithubData(updateData, user);

        // then
        assertEquals(updateData, user.getGithub());
        verify(userRepository).save(user);
    }

    @Test
    void updateGithubData_should_throw_exception_if_update_data_is_null() {
        // given
        String updateData = null;

        // when and then
        assertThrows(IllegalArgumentException.class, () -> userService.updateGithubData(updateData, user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateGithubData_should_throw_exception_if_update_data_is_empty() {
        // given
        String updateData = "";

        // when and then
        assertThrows(IllegalArgumentException.class, () -> userService.updateGithubData(updateData, user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateUsersLocation_should_update_user_and_save_it() {
        // given
        String updateData = "New York";

        // when
        userService.updateUsersLocation(updateData, user);

        // then
        assertEquals(updateData, user.getLocation());
        verify(userRepository).save(user);
    }

    @Test
    void updateUsersLocation_should_throw_exception_if_update_data_is_null() {
        // given
        String updateData = null;

        // when and then
        assertThrows(IllegalArgumentException.class, () -> userService.updateUsersLocation(updateData, user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateUsersLocation_should_throw_exception_if_update_data_is_empty() {
        // given
        String updateData = "";

        // when and then
        assertThrows(IllegalArgumentException.class, () -> userService.updateUsersLocation(updateData, user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateUsersJob_should_update_user_and_save_it() {
        // given
        String updateData = "Software Engineer";

        // when
        userService.updateUsersJob(updateData, user);

        // then
        assertEquals(updateData, user.getJob());
        verify(userRepository).save(user);
    }

    @Test
    void updateUsersJob_should_throw_exception_if_update_data_is_null() {
        // given
        String updateData = null;

        // when and then
        assertThrows(IllegalArgumentException.class, () -> userService.updateUsersJob(updateData, user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateUsersJob_should_throw_exception_if_update_data_is_empty() {
        // given
        String updateData = "";

        // when and then
        assertThrows(IllegalArgumentException.class, () -> userService.updateUsersJob(updateData, user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void removeFriendFromUser_should_delete_friend_from_repository() {
        // given
        String friendsUsername = "jane.doe@example.com";

        when(friendsRepository.findByFriendUsernameAndUser(friendsUsername, user)).thenReturn(Optional.of(friendEntity));

        // when
        userService.removeFriendFromUser(friendsUsername, user);

        // then
        verify(friendsRepository).delete(friendEntity);
    }

    @Test
    void removeFriendFromUser_should_throw_exception_if_friend_does_not_exist() {
        // given
        String friendsUsername = "jane.doe@example.com";

        when(friendsRepository.findByFriendUsernameAndUser(friendsUsername, user)).thenReturn(Optional.empty());

        // when and then
        assertThrows(FriendNotFoundException.class, () -> userService.removeFriendFromUser(friendsUsername, user));
        verify(friendsRepository, never()).delete(any(Friend.class));
    }

    @Test
    void addFriendToUser_should_save_friend_to_repository() {
        // given
        String friendsUsername = "jane.doe@example.com";

        when(friendsRepository.findByFriendUsernameAndUser(friendsUsername, user)).thenReturn(Optional.empty());
        when(userRepository.findByEmail(friendsUsername)).thenReturn(Optional.of(friend));

        // when
        userService.addFriendToUser(friendsUsername, user);

        // then
        verify(friendsRepository).save(any(Friend.class));
    }

    @Test
    void addFriendToUser_should_throw_exception_if_user_already_exists() {
        // given
        String friendsUsername = "jane.doe@example.com";

        when(friendsRepository.findByFriendUsernameAndUser(friendsUsername, user)).thenReturn(Optional.of(friendEntity));

        // when and then
        assertThrows(UsersAlreadyFriendsException.class, () -> userService.addFriendToUser(friendsUsername, user));
        verify(friendsRepository, never()).save(any(Friend.class));
    }

    @Test
    void addFriendToUser_should_throw_exception_if_user_does_not_exist() {
        // given
        String friendsUsername = "jane.doe@example.com";

        when(friendsRepository.findByFriendUsernameAndUser(friendsUsername, user)).thenReturn(Optional.empty());
        when(userRepository.findByEmail(friendsUsername)).thenReturn(Optional.empty());

        // when and then
        assertThrows(UsernameNotFoundException.class, () -> userService.addFriendToUser(friendsUsername, user));
        verify(friendsRepository, never()).save(any(Friend.class));
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

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(jacek));

        // when
        userService.updateTwitterData(expected, jacek);

        String result = userRepository.findByEmail(jacek.getEmail()).get().getTwitter();

        // then
        assertEquals(expected, result);
        verify(userRepository).save(jacek);
    }

    @Test
    final void test_updateGithubData_correct_usage() {
        // given
        String expected = "myUrlData";

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(jacek));

        // when
        userService.updateGithubData(expected, jacek);

        String result = userRepository.findByEmail(jacek.getEmail()).get().getGithub();

        // then
        assertEquals(expected, result);
        verify(userRepository).save(jacek);
    }

    @Test
    final void test_updateLinkedinData_correct_usage() {
        // given
        String expected = "myUrlData";

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(jacek));

        // when
        userService.updateLinkedinData(expected, jacek);

        String result = userRepository.findByEmail(jacek.getEmail()).get().getLinkedin();

        // then
        assertEquals(expected, result);
        verify(userRepository).save(jacek);
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

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(jacek));

        // when
        userService.updateUsersLocation(expected, jacek);
        String result = userRepository.findByEmail(jacek.getEmail()).get().getLocation();

        // then
        assertEquals(expected, result);
        verify(userRepository).save(jacek);
    }

    @Test
    final void test_updateUsersJob_correct_usage() {
        // given
        String expected = "myUrlData";

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(jacek));

        // when
        userService.updateUsersJob(expected, jacek);

        String result = userRepository.findByEmail(jacek.getEmail()).get().getJob();

        // then
        assertEquals(expected, result);
        verify(userRepository).save(jacek);
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
