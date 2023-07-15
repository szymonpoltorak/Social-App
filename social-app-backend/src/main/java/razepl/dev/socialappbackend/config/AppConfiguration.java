package razepl.dev.socialappbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import razepl.dev.socialappbackend.config.enums.Role;
import razepl.dev.socialappbackend.config.interfaces.AppConfigInterface;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.exceptions.AuthManagerInstanceException;

import java.time.LocalDate;
import java.util.List;

import static razepl.dev.socialappbackend.config.constants.CorsConfig.*;
import static razepl.dev.socialappbackend.config.constants.Headers.AUTH_HEADER;

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
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        configuration.setAllowedOrigins(CORS_ADDRESSES);
        configuration.setAllowedMethods(ALLOWED_REQUESTS);
        configuration.setAllowedHeaders(List.of(AUTH_HEADER, CONTENT_TYPE_HEADER));

        source.registerCorsConfiguration(API_PATTERN, configuration);

        return source;
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

    @Bean
    @Override
    public CommandLineRunner commandLineRunner() {
        return args -> {
            User admin = User
                    .builder()
                    .name("Admin")
                    .surname("admin")
                    .role(Role.ADMIN)
                    .email("admin@gmail.com")
                    .password(passwordEncoder().encode("Ab!#$123zncA"))
                    .dateOfBirth(LocalDate.parse("2000-01-01"))
                    .build();
            userRepository.save(admin);

            User moderator = User
                    .builder()
                    .name("moderator")
                    .surname("moderator")
                    .role(Role.MODERATOR)
                    .email("moderator@gmail.com")
                    .password(passwordEncoder().encode("Ab!#$789asdhjaksA"))
                    .dateOfBirth(LocalDate.parse("2000-01-01"))
                    .build();
            userRepository.save(moderator);
        };
    }
}
