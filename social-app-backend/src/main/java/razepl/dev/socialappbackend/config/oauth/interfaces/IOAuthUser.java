package razepl.dev.socialappbackend.config.oauth.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.time.LocalDate;

/**
 * This interface extends the OidcUser and UserDetails interfaces and represents an OAuth user.
 */
public interface IOAuthUser extends OidcUser, UserDetails {
    /**
     * Retrieves the ID of the OAuth user.
     *
     * @return The ID of the OAuth user.
     */
    String getId();

    /**
     * Retrieves the family name of the OAuth user.
     *
     * @return The family name of the OAuth user.
     */
    @Override
    String getFamilyName();

    /**
     * Retrieves the birth date of the OAuth user.
     *
     * @return The birth date of the OAuth user.
     */
    LocalDate getBirthDate();

    /**
     * Retrieves the location of the OAuth user.
     *
     * @return The location of the OAuth user.
     */
    String getLocation();

    /**
     * Retrieves the GitHub username of the OAuth user.
     *
     * @return The GitHub username of the OAuth user.
     */
    String getGithub();
}

