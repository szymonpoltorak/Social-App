package razepl.dev.socialappbackend.auth.jwt;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import razepl.dev.socialappbackend.entities.jwt.interfaces.TokenManager;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;
import razepl.dev.socialappbackend.entities.user.User;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TokenManagerServiceTest {
    @Autowired
    private TokenManager tokenManager;

    @Test
    final void test_saveUserToken_args_nulls() {
        // given
        String jwtToken = null;
        User user = null;

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> tokenManager.saveUsersToken(jwtToken, user));
    }

    @Test
    final void test_buildTokensIntoResponse_tokens_args_nulls() {
        // given
        String jwtToken = null;

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> tokenManager.buildTokensIntoResponse(jwtToken, jwtToken));
    }

    @Test
    final void test_buildTokensIntoResponse_user_args_nulls() {
        // given
        User user = null;

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> tokenManager.buildTokensIntoResponse(user, false));
    }

    @Test
    final void test_revokeUserTokens_args_nulls() {
        // given
        User user = null;

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> tokenManager.revokeUserTokens(user));
    }
}
