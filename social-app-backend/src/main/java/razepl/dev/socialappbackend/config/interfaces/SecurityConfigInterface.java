package razepl.dev.socialappbackend.config.interfaces;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The SecurityConfigInterface interface provides a method to define the security filter chain
 * for the application.
 */
public interface SecurityConfigInterface {

    /**
     * Configures the security filter chain for the application.
     *
     * @param httpSecurity the HttpSecurity instance to configure
     * @return the configured SecurityFilterChain instance
     */
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity);
}
