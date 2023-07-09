package razepl.dev.socialappbackend.config.oidc.interfaces;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import razepl.dev.socialappbackend.entities.user.User;

public interface IOidcUserService {
    IOidcUser getOidcUser(String registrationId, OidcUser oidcUser);

    User updateExisitingUser(User user, IOidcUser oidcUser);

    User registerOidcUser(IOidcUser oidcUser);
}
