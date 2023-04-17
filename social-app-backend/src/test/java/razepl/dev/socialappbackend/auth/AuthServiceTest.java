package razepl.dev.socialappbackend.auth;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import razepl.dev.socialappbackend.auth.apicalls.RegisterRequest;
import razepl.dev.socialappbackend.auth.interfaces.AuthServiceInterface;
import razepl.dev.socialappbackend.auth.interfaces.RegisterUserRequest;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;
import razepl.dev.socialappbackend.exceptions.PasswordValidationException;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthServiceTest {
    @Autowired
    private AuthServiceInterface authService;

    @Test
    final void test_loginUser_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> authService.login(null));
    }

    @Test
    final void test_registerUser_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> authService.register(null));
    }

    @Test
    final void test_refreshUserToken_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> authService.refreshToken(null, null));
    }

    @Test
    final void test_register_wrong_password() {
        // given
        RegisterUserRequest request = new RegisterRequest("name", "surname", "email", "password", LocalDate.now());

        // when

        // then
        Assertions.assertThrows(PasswordValidationException.class, () -> authService.register(request));
    }
}
