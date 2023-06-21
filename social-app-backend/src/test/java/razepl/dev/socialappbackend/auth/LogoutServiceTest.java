package razepl.dev.socialappbackend.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import razepl.dev.socialappbackend.entities.jwt.JwtToken;
import razepl.dev.socialappbackend.entities.jwt.interfaces.TokenRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class LogoutServiceTest {
    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private LogoutService logoutService;

    @Test
    final void test_logout_shouldDoNothingIfAuthHeaderIsNull() {
        // given
        when(request.getHeader(any()))
                .thenReturn(null);

        // when
        logoutService.logout(request, response, authentication);

        // then
        verify(tokenRepository, never()).findByToken(any());
        verify(tokenRepository, never()).save(any());
    }

    @Test
    final void test_logout_shouldDoNothingIfAuthHeaderDoesNotStartWithBearer() {
        // given
        when(request.getHeader(any()))
                .thenReturn("Basic abcdef");

        // when
        logoutService.logout(request, response, authentication);

        // then
        verify(tokenRepository, never()).findByToken(any());
        verify(tokenRepository, never()).save(any());
    }

    @Test
    final void test_logout_shouldDoNothingIfJwtTokenIsNotFound() {
        // given
        when(request.getHeader(any()))
                .thenReturn("Bearer abcdef");

        when(tokenRepository.findByToken(any()))
                .thenReturn(java.util.Optional.empty());

        // when
        logoutService.logout(request, response, authentication);

        // then
        verify(tokenRepository).findByToken(any());
        verify(tokenRepository, never()).save(any());
    }

    @Test
    final void test_logout_shouldSetJwtTokenAsExpiredAndRevokedAndClearSecurityContextIfFound() {
        // given
        when(request.getHeader(any()))
                .thenReturn("Bearer abcdef");

        JwtToken token = JwtToken
                .builder()
                .token("abcdef")
                .build();

        when(tokenRepository.findByToken(any()))
                .thenReturn(java.util.Optional.of(token));

        // when
        logoutService.logout(request, response, authentication);

        // then
        verify(tokenRepository).findByToken(any());
        verify(tokenRepository).save(any());
        assertEquals(true, token.isExpired());
        assertEquals(true, token.isRevoked());
        SecurityContextHolder.clearContext();
    }
}
