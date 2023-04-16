package razepl.dev.socialappbackend.config.interfaces;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public interface SecurityConfigInterface {
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity);
}
