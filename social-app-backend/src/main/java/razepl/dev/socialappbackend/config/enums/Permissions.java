package razepl.dev.socialappbackend.config.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permissions {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_UPDATE("admin:update"),
    MODERATOR_READ("moderator:read"),
    MODERATOR_WRITE("moderator:write"),
    MODERATOR_DELETE("moderator:delete"),
    MODERATOR_UPDATE("moderator:update");

    private final String permission;
}
