package razepl.dev.socialappbackend.config.oidc.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.time.LocalDate;

public interface IOidcUser extends OidcUser, UserDetails {
    LocalDate getBirthDate();
}
