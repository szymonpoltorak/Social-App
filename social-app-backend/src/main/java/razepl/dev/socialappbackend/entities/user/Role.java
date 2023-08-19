package razepl.dev.socialappbackend.entities.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import razepl.dev.socialappbackend.config.enums.Permissions;
import razepl.dev.socialappbackend.config.interfaces.AuthoritiesOwner;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An enumeration representing user roles.
 * Available roles are USER, ADMIN and MODERATOR.
 */
@Getter
@RequiredArgsConstructor
public enum Role implements AuthoritiesOwner {
    USER(
            Collections.emptySet()
    ),
    ADMIN(
            Set.of(
                    Permissions.ADMIN_READ,
                    Permissions.ADMIN_WRITE,
                    Permissions.ADMIN_DELETE,
                    Permissions.ADMIN_UPDATE,
                    Permissions.MODERATOR_READ,
                    Permissions.MODERATOR_WRITE,
                    Permissions.MODERATOR_DELETE,
                    Permissions.MODERATOR_UPDATE
            )
    ),
    MODERATOR(
            Set.of(
                    Permissions.MODERATOR_READ,
                    Permissions.MODERATOR_WRITE,
                    Permissions.MODERATOR_DELETE,
                    Permissions.MODERATOR_UPDATE
            )
    );

    private final Set<Permissions> permissions;

    @Override
    public final List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = permissions
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}
