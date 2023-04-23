package razepl.dev.socialappbackend.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import razepl.dev.socialappbackend.auth.apicalls.RegisterRequest;
import razepl.dev.socialappbackend.auth.apicalls.TokenRequest;
import razepl.dev.socialappbackend.auth.apicalls.TokenResponse;
import razepl.dev.socialappbackend.auth.interfaces.AuthServiceInterface;
import razepl.dev.socialappbackend.auth.interfaces.RegisterUserRequest;
import razepl.dev.socialappbackend.auth.jwt.JwtToken;
import razepl.dev.socialappbackend.auth.jwt.interfaces.TokenRepository;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;
import razepl.dev.socialappbackend.exceptions.PasswordValidationException;
import razepl.dev.socialappbackend.exceptions.TokensUserNotFoundException;
import razepl.dev.socialappbackend.util.AuthTestUtil;

import java.time.LocalDate;
import java.util.Map;

import static razepl.dev.socialappbackend.constants.ApiRequests.REGISTER_REQUEST;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthServiceInterface authService;

    @Autowired
    private TokenRepository tokenRepository;

    @Test
    final void test_validateUsersTokens() throws Exception {
        // given
        String password = "Abc1!l1.DKk";
        RegisterUserRequest registerRequest = AuthTestUtil.createUserForRegister(password);
        ObjectMapper objectMapper = new ObjectMapper();
        TokenResponse expected = TokenResponse.builder().isAuthTokenValid(true).build();

        // when
        String response = mockMvc.perform(MockMvcRequestBuilders.post(REGISTER_REQUEST)
                        .content(AuthTestUtil.asJsonString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Map<String, String> responseMap = objectMapper.readValue(response, new TypeReference<>() {
        });
        System.out.println(responseMap.toString());

        TokenRequest request = TokenRequest.builder()
                .authToken(responseMap.get("authToken"))
                .build();
        System.out.println(request.toString());

        TokenResponse result = authService.validateUsersTokens(request);

        Assertions.assertEquals(expected, result, "Objects differs from each other!");
    }

    @Test
    final void test_validateUsersTokens_not_existing_tokens() {
        // given
        TokenRequest request = TokenRequest.builder()
                .authToken("")
                .build();

        // when

        // then

        Assertions.assertThrows(TokensUserNotFoundException.class, () -> authService.validateUsersTokens(request));
    }

    @Test
    final void test_validateUsersTokens_nulls() {
        // given

        // when

        // then
        Assertions.assertThrows(NullArgumentException.class, () -> authService.validateUsersTokens(null));
    }

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

    private void revokeUsersTokens(String token) {
        JwtToken jwtToken = tokenRepository.findByToken(token).orElseThrow();

        jwtToken.setExpired(true);
        jwtToken.setRevoked(true);

        tokenRepository.save(jwtToken);
    }
}
