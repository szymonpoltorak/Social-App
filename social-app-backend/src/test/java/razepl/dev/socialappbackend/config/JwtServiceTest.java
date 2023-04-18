package razepl.dev.socialappbackend.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import razepl.dev.socialappbackend.config.interfaces.JwtServiceInterface;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;

@SpringBootTest
class JwtServiceTest {
    @Autowired
    private JwtServiceInterface jwtService;

    @Test
    final void test_getUsernameFromToken_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> jwtService.getUsernameFromToken(null));
    }

    @Test
    final void test_getClaimFromToken_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> jwtService.getClaimFromToken(null, null));
    }

    @Test
    final void test_generateRefreshToken_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> jwtService.generateRefreshToken(null));
    }

    @Test
    final void test_generateToken_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> jwtService.generateToken(null));
    }

    @Test
    final void test_generateToken_with_map_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> jwtService.generateToken(null, null, 0L));
    }

    @Test
    final void test_isTokenValid_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> jwtService.isTokenValid(null, null));
    }

    @Test
    final void test_getJwtToken_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> jwtService.getJwtToken(null));
    }

    @Test
    final void test_getJwtRefreshToken_args_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> jwtService.getJwtRefreshToken(null));
    }
}
