package razepl.dev.socialappbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import razepl.dev.socialappbackend.config.interfaces.AppConfigInterface;
import razepl.dev.socialappbackend.exceptions.AuthManagerInstanceException;
import razepl.dev.socialappbackend.user.interfaces.UserRepository;

/**
 * Class made to provide necessary Beans for Spring app.
 * It implements {@link AppConfigInterface}.
 */
@RequiredArgsConstructor
@Configuration
public class AppConfiguration implements AppConfigInterface {
    private final UserRepository userRepository;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Bean
    @Override
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    @Override
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws AuthManagerInstanceException {
        try {
            return configuration.getAuthenticationManager();
        } catch (Exception exception) {
            throw new AuthManagerInstanceException("Could not create authManager bean!");
        }
    }
}
