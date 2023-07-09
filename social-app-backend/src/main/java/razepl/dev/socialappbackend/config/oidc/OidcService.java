package razepl.dev.socialappbackend.config.oidc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.config.oidc.interfaces.IOidcService;
import razepl.dev.socialappbackend.config.oidc.interfaces.IOidcUser;
import razepl.dev.socialappbackend.config.oidc.interfaces.IOidcUserService;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OidcService extends OidcUserService implements IOidcService {
    private final UserRepository userRepository;
    private final IOidcUserService oidcUserService;

    @Override
    public final OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        return processOidcUserAuthRequest(userRequest, oidcUser);
    }

    private OidcUser processOidcUserAuthRequest(OAuth2UserRequest userRequest, OidcUser oidcUser) {
        IOidcUser newOidcUser = oidcUserService.getOidcUser(
                userRequest.getClientRegistration().getRegistrationId(),
                oidcUser
        );
        log.error("Email : {}", oidcUser.getAttributes().get("email"));
        String username = newOidcUser.getUsername();

        log.info("Username: {}", username);

        if (username == null || username.isEmpty()) {
            throw new OAuth2AuthenticationException("Username has not been found!");
        }
        Optional<User> authenticatedUser = userRepository.findByEmail(username);

        if (authenticatedUser.isPresent()) {
            oidcUserService.updateExistingUser(authenticatedUser.get(), newOidcUser);

            return newOidcUser;
        }
        oidcUserService.registerOidcUser(newOidcUser);

        return newOidcUser;
    }
}
