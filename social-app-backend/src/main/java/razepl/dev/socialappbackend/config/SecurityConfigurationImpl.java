package razepl.dev.socialappbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import razepl.dev.socialappbackend.config.interfaces.SecurityConfiguration;
import razepl.dev.socialappbackend.config.jwt.interfaces.JwtAuthenticationFilter;
import razepl.dev.socialappbackend.exceptions.SecurityChainException;

import static razepl.dev.socialappbackend.config.constants.Headers.*;
import static razepl.dev.socialappbackend.config.enums.Permissions.*;
import static razepl.dev.socialappbackend.entities.user.Role.ADMIN;
import static razepl.dev.socialappbackend.entities.user.Role.MODERATOR;

/**
 * Class made to configure security filter chain in app.
 * It implements {@link SecurityConfiguration}.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        jsr250Enabled = true,
        securedEnabled = true
)
@RequiredArgsConstructor
public class SecurityConfigurationImpl implements SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final LogoutHandler logoutHandler;
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oauthService;
    private final AuthenticationFailureHandler authFailureHandler;
    private final AuthenticationSuccessHandler authSuccessHandler;
    private final OAuth2UserService<OidcUserRequest, OidcUser> oidcService;

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

                            .requestMatchers(ADMIN_MATCHERS).hasRole(ADMIN.name())

                            .requestMatchers(HttpMethod.GET, ADMIN_MATCHERS)
                            .hasAuthority(ADMIN_READ.name())

                            .requestMatchers(HttpMethod.POST, ADMIN_MATCHERS)
                            .hasAuthority(ADMIN_WRITE.name())

                            .requestMatchers(HttpMethod.PATCH, ADMIN_MATCHERS)
                            .hasAuthority(ADMIN_UPDATE.name())

                            .requestMatchers(HttpMethod.DELETE, ADMIN_MATCHERS)
                            .hasAuthority(ADMIN_DELETE.name())

                            .requestMatchers(MODERATOR_MATCHERS).hasAnyRole(ADMIN.name(), MODERATOR.name())

                            .requestMatchers(HttpMethod.GET, MODERATOR_MATCHERS)
                            .hasAnyAuthority(ADMIN_READ.name(), MODERATOR_READ.name())

                            .requestMatchers(HttpMethod.POST, MODERATOR_MATCHERS)
                            .hasAnyAuthority(ADMIN_WRITE.name(), MODERATOR_WRITE.name())

                            .requestMatchers(HttpMethod.PATCH, MODERATOR_MATCHERS)
                            .hasAnyAuthority(ADMIN_UPDATE.name(), MODERATOR_UPDATE.name())

                            .requestMatchers(HttpMethod.DELETE, MODERATOR_MATCHERS)
                            .hasAnyAuthority(ADMIN_DELETE.name(), MODERATOR_DELETE.name())

                            .anyRequest()
                            .authenticated()
                    )
                    .cors()
                    .and()
                    .sessionManagement(
                            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .oauth2Login(oauth -> oauth
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
