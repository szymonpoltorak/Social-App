package razepl.dev.socialappbackend.config.interfaces;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import razepl.dev.socialappbackend.exceptions.AuthManagerInstanceException;

/**
 * This interface defines the necessary configurations for the authentication process.
 */
public interface AppConfigInterface {
    /**
     * Returns the UserDetailsService to be used for authentication.
     *
     * @return the UserDetailsService.
     */
    UserDetailsService userDetailsService();

    /**
     * Returns the AuthenticationProvider to be used for authentication.
     *
     * @return the AuthenticationProvider.
     */
    AuthenticationProvider authenticationProvider();

    /**
     * Returns the PasswordEncoder to be used for authentication.
     *
     * @return the PasswordEncoder.
     */
    PasswordEncoder passwordEncoder();

    /**
     * Returns the AuthenticationManager to be used for authentication.
     *
     * @param configuration the {AuthenticationConfiguration} to be used for authentication.
     * @return the AuthenticationManager.
     * @throws AuthManagerInstanceException if there is an error instantiating the AuthenticationManager.
     */
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws AuthManagerInstanceException;
}
