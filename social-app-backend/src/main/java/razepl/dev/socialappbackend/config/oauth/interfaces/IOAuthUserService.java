package razepl.dev.socialappbackend.config.oauth.interfaces;

import razepl.dev.socialappbackend.entities.user.User;

import java.util.Map;

public interface IOAuthUserService {
    IOAuthUser getOAuthUser(String registrationId, Map<String, Object> attributes);

    User updateExisitingUser(User user, IOAuthUser oAuthUser);

    User registerOAuthUser(IOAuthUser oAuthUser);
}
