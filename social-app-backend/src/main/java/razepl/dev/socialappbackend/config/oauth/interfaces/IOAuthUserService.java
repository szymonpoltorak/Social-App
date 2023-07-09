package razepl.dev.socialappbackend.config.oauth.interfaces;

import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.ServiceUser;

import java.util.Map;

public interface IOAuthUserService {
    IOAuthUser getOAuthUser(String registrationId, Map<String, Object> attributes);

    ServiceUser updateExistingUser(User user, IOAuthUser oAuthUser);

    ServiceUser registerOAuthUser(IOAuthUser oAuthUser);
}
