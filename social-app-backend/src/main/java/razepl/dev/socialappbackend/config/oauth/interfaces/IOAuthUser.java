package razepl.dev.socialappbackend.config.oauth.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDate;

public interface IOAuthUser extends OAuth2User, UserDetails {
    String getId();

    String getFamilyName();

    LocalDate getBirthdate();
}
