package razepl.dev.socialappbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import razepl.dev.socialappbackend.config.interfaces.SecurityConfigInterface;
import razepl.dev.socialappbackend.config.jwt.interfaces.JwtFilter;
import razepl.dev.socialappbackend.config.handlers.interfaces.IOAuthFailureHandler;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthService;
import razepl.dev.socialappbackend.config.handlers.interfaces.IOAuthSuccessHandler;
import razepl.dev.socialappbackend.config.oidc.interfaces.IOidcService;
import razepl.dev.socialappbackend.exceptions.SecurityChainException;

import static razepl.dev.socialappbackend.config.constants.Headers.LOGOUT_URL;
import static razepl.dev.socialappbackend.config.constants.Headers.WHITE_LIST;

/**
 * Class made to configure security filter chain in app.
 * It implements {@link SecurityConfigInterface}.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        jsr250Enabled = true,
        securedEnabled = true
)
@RequiredArgsConstructor
public class SecurityConfiguration implements SecurityConfigInterface {
    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtAuthenticationFilter;
    private final LogoutHandler logoutHandler;
    private final IOAuthService oauthService;
    private final IOAuthFailureHandler authFailureHandler;
    private final IOAuthSuccessHandler authSuccessHandler;
    private final IOidcService oidcService;

    @Bean
    @Override
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        try {
            httpSecurity
                    .csrf()
                    .disable()
                    .authorizeHttpRequests(request -> request
                            .requestMatchers(WHITE_LIST)
                            .permitAll()
                            .anyRequest()
                            .authenticated()
                    )
                    .cors()
                    .and()
                    .sessionManagement(
                            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .oauth2Login(oauth -> oauth
                            .authorizationEndpoint(endpoint -> endpoint
                                    .baseUri("/login/oauth2/authorization")
                            )
                            .userInfoEndpoint()
                            .oidcUserService(oidcService)
                            .userService(oauthService)
                            .and()
                            .failureHandler(authFailureHandler)
                            .successHandler(authSuccessHandler)
                    )
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .logout(logout -> logout
                            .logoutUrl(LOGOUT_URL)
                            .addLogoutHandler(logoutHandler)
                            .logoutSuccessHandler(
                                    (request, response, authentication) -> SecurityContextHolder.clearContext()
                            )
                    );
            return httpSecurity.build();
        } catch (Exception exception) {
            throw new SecurityChainException("Security chain has come with an error!");
        }
    }
}
