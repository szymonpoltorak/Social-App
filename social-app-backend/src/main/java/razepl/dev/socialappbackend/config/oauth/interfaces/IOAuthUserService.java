package razepl.dev.socialappbackend.config.oauth.interfaces;

import org.springframework.security.oauth2.core.user.OAuth2User;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.ServiceUser;

public interface IOAuthUserService {
    <T extends OAuth2User> IOAuthUser getOAuthUser(String registrationId, T oauthUser);

    ServiceUser updateExistingUser(User user, IOAuthUser oidcUser);

    ServiceUser registerOAuthUser(IOAuthUser oidcUser);
}
