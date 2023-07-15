package razepl.dev.socialappbackend.config.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.config.jwt.interfaces.JwtServiceInterface;
import razepl.dev.socialappbackend.config.jwt.interfaces.TokenManager;
import razepl.dev.socialappbackend.entities.token.JwtToken;
import razepl.dev.socialappbackend.entities.token.interfaces.TokenRepository;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TokenManagerServiceTest {
    @Autowired
    private TokenManager tokenManager;

    @Mock
    private TokenRepository mockTokenRepository;

    @Mock
    private JwtServiceInterface mockJwtService;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private TokenManager tokenManagerService;

    @BeforeEach
    final void setUp() {
        MockitoAnnotations.openMocks(this);
        tokenManagerService = new TokenManagerService(mockTokenRepository, mockJwtService, mockUserRepository);
    }

    @Test
    final void saveUsersToken_ValidTokenAndUser_TokenSavedToRepository() {
        // given
        String jwtToken = "valid-token";
        User user = new User();

        // when
        tokenManagerService.saveUsersToken(jwtToken, user);

        // then
        verify(mockTokenRepository).save(any(JwtToken.class));
    }

    @Test
    final void buildTokensIntoResponse_AuthTokenAndRefreshToken_ReturnsAuthResponse() {
        // given
        String authToken = "auth-token";
        String refreshToken = "refresh-token";

        // when
        AuthResponse authResponse = tokenManagerService.buildTokensIntoResponse(authToken, refreshToken);

        // then
        assertNotNull(authResponse);
        assertEquals(authToken, authResponse.authToken());
        assertEquals(refreshToken, authResponse.refreshToken());
    }

    @Test
    final void buildTokensIntoResponse_UserAndShouldRevokeTokens_ReturnsAuthResponse() {
        // given
        User user = new User();
        String authToken = "auth-token";
        String refreshToken = "refresh-token";

        // when
        when(mockJwtService.generateToken(user)).thenReturn(authToken);
        when(mockJwtService.generateRefreshToken(user)).thenReturn(refreshToken);

        AuthResponse authResponse = tokenManagerService.buildTokensIntoResponse(user, true);

        // then
        assertNotNull(authResponse);
        assertEquals(authToken, authResponse.authToken());
        assertEquals(refreshToken, authResponse.refreshToken());

        verify(mockJwtService).generateToken(user);
        verify(mockJwtService).generateRefreshToken(user);
        verify(mockTokenRepository).save(any(JwtToken.class));
    }

    @Test
    final void revokeUserTokens_ValidUser_TokensRevokedAndSavedToRepository() {
        // given
        User user = new User();
        JwtToken token1 = new JwtToken();
        JwtToken token2 = new JwtToken();
        List<JwtToken> userTokens = new ArrayList<>(100);

        userTokens.add(token1);
        userTokens.add(token2);

        // when
        when(mockTokenRepository.findAllValidTokensByUserId(user.getUserId())).thenReturn(userTokens);

        tokenManagerService.revokeUserTokens(user);

        // then
        assertTrue(token1.isRevoked());
        assertTrue(token1.isExpired());
        assertTrue(token2.isRevoked());
        assertTrue(token2.isExpired());

        verify(mockTokenRepository).findAllValidTokensByUserId(user.getUserId());
        verify(mockTokenRepository).saveAll(userTokens);
    }

    @Test
    final void revokeUserTokens_ValidUser_NoTokensToRevoke() {
        // given
        User user = new User();

        // when
        when(mockTokenRepository.findAllValidTokensByUserId(user.getUserId()))
                .thenReturn(new ArrayList<>(100));

        tokenManagerService.revokeUserTokens(user);

        // then
        verify(mockTokenRepository).findAllValidTokensByUserId(user.getUserId());
        verify(mockTokenRepository, never()).saveAll(anyList());
    }

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
