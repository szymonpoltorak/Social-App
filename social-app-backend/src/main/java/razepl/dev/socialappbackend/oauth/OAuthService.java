package razepl.dev.socialappbackend.oauth;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.oauth.interfaces.IOAuthService;

@Service
public class OAuthService implements IOAuthService {
    @Override
    public final OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        return null;
    }
}
