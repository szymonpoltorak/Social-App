package razepl.dev.socialappbackend.config.oidc.data;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import razepl.dev.socialappbackend.config.oidc.interfaces.IOidcUser;
import razepl.dev.socialappbackend.entities.user.User;

import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class OidcUserPrincipal implements IOidcUser {
    private Long id;
    private String username;
    private String password;
    private String surname;
    private String phoneNumber;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public OidcUserPrincipal(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static OidcUserPrincipal create(User user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new OidcUserPrincipal(
                user.getUserId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public static OidcUserPrincipal create(User user, Map<String, Object> attributes) {
        OidcUserPrincipal userPrincipal = OidcUserPrincipal.create(user);

        userPrincipal.setAttributes(attributes);

        return userPrincipal;
    }

    @Override
    public final String getPassword() {
        return password;
    }

    @Override
    public final String getUsername() {
        return username;
    }

    @Override
    public final boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public final boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public final boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public final boolean isEnabled() {
        return true;
    }

    @Override
    public final Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    @Override
    public final Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableCollection(authorities);
    }

    @Override
    public final String getName() {
        return String.valueOf(id);
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new NotSerializableException("razepl.dev.socialappbackend.config.oauth.data.OAuthUserPrincipal");
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        throw new NotSerializableException("razepl.dev.socialappbackend.config.oauth.data.OAuthUserPrincipal");
    }

    @Override
    public final Map<String, Object> getClaims() {
        return null;
    }

    @Override
    public final OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public final OidcIdToken getIdToken() {
        return null;
    }
}
