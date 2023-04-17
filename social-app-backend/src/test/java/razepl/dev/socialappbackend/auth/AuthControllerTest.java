package razepl.dev.socialappbackend.auth;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import razepl.dev.socialappbackend.auth.interfaces.AuthInterface;
import razepl.dev.socialappbackend.auth.interfaces.RegisterUserRequest;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;
import razepl.dev.socialappbackend.user.User;
import razepl.dev.socialappbackend.user.interfaces.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static razepl.dev.socialappbackend.constants.ApiRequests.REGISTER_REQUEST;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthInterface authInterface;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    final void init() {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password", authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    final void test_registerUser_successful_register() throws Exception {
        // given
        String password = "Abc1!l1.DKk";
        String name = "Adam";
        String surname = "Kowalski";
        String email = "andrzej@gmail.com";
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);

        RegisterUserRequest user = AuthTestUtil.buildUserRequest(dateOfBirth, name, surname, email, password);

        // when
        mockMvc.perform(MockMvcRequestBuilders.post(REGISTER_REQUEST)
                        .content(AuthTestUtil.asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Optional<User> result = userRepository.findByName(name);

        // then
        Assertions.assertNotNull(result, "Registering user has failed!");
    }

    @ParameterizedTest
    @CsvSource({
            "Abcdla.dkk",
            "Abc1al1dDKk",
            "Abca!ln.DKk",
            "a",
            "ABCDEFGHIJK",
            "abcdefghijk"
    })
    final void test_registerUser_parametrized(String password) throws Exception {
        // given
        Optional<User> expected = Optional.empty();
        String name = "Adam";
        String surname = "Kowalski";
        String email = "andrzej@gmail.com";
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);

        RegisterUserRequest user = AuthTestUtil.buildUserRequest(dateOfBirth, name, surname, email, password);

        // when
        mockMvc.perform(MockMvcRequestBuilders.post(REGISTER_REQUEST)
                        .content(AuthTestUtil.asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());

        Optional<User> result = userRepository.findByName(name);

        // then
        Assertions.assertEquals(expected, result, "Registering user has failed!");
    }

    @Test
    final void test_loginUser_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> authInterface.loginUser(null));
    }

    @Test
    final void test_registerUser_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> authInterface.registerUser(null));
    }

    @Test
    final void test_refreshUserToken_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> authInterface.refreshUserToken(null, null));
    }
}
