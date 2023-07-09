package razepl.dev.socialappbackend.config.oauth.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface IOAuthUserPrincipal extends OAuth2User, UserDetails {
}
