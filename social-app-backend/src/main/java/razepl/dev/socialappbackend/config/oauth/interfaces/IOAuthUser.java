package razepl.dev.socialappbackend.config.oauth.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.time.LocalDate;

public interface IOAuthUser extends OidcUser, UserDetails {
    String getId();

    @Override
    String getFamilyName();

    LocalDate getBirthDate();
}
