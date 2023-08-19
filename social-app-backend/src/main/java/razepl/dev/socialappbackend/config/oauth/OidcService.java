package razepl.dev.socialappbackend.config.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.config.oauth.interfaces.OAuthUserProcessor;

@Slf4j
@Service
@RequiredArgsConstructor
public class OidcService extends OidcUserService {
    private final OAuthUserProcessor<OidcUserRequest, OidcUser> oauthUserProcessor;

    @Override
    public final OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        log.info("Loading OIDC user.");

        return oauthUserProcessor.processOAuthUserRegistration(userRequest, oidcUser);
    }
}
