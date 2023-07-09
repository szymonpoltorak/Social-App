package razepl.dev.socialappbackend.config.oidc.interfaces;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.ServiceUser;

public interface IOidcUserService {
    IOidcUser getOidcUser(String registrationId, OidcUser oidcUser);

    ServiceUser updateExistingUser(User user, IOidcUser oidcUser);

    ServiceUser registerOidcUser(IOidcUser oidcUser);
}
