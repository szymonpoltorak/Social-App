package razepl.dev.socialappbackend.config.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUser;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Data
@Builder
public class GoogleOidcUser implements IOAuthUser {
    private String password;
    private String username;
    private Map<String, Object> claims;
    private OidcUserInfo userInfo;
    private OidcIdToken idToken;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;
    private String name;
    private String familyName;
    private LocalDate birthdate;

    @Override
    public final LocalDate getBirthDate() {
        return birthdate;
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
    public final String getId() {
        return attributes.get("sub").toString();
    }

    @Override
    public final String getFamilyName() {
        return familyName;
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
    public final Map<String, Object> getClaims() {
        return Collections.unmodifiableMap(claims);
    }

    @Override
    public final OidcUserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public final OidcIdToken getIdToken() {
        return idToken;
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
        return name;
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new NotSerializableException("razepl.dev.socialappbackend.config.oauth.data.OAuthUserPrincipal");
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        throw new NotSerializableException("razepl.dev.socialappbackend.config.oauth.data.OAuthUserPrincipal");
    }
}
