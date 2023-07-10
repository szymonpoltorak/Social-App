package razepl.dev.socialappbackend.config.oauth.interfaces;

import org.springframework.security.oauth2.core.user.OAuth2User;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.ServiceUser;

public interface IOAuthUserService {
    IOAuthUser getOAuthUser(String registrationId, OAuth2User oAuth2User);

    ServiceUser updateExistingUser(User user, IOAuthUser oAuthUser);

    ServiceUser registerOAuthUser(IOAuthUser oAuthUser);
}
