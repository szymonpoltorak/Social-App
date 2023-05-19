package razepl.dev.socialappbackend.home.data;

import lombok.Builder;
import razepl.dev.socialappbackend.globals.EntityData;

/**
 * This record represents friend data, including the friend's full name, job, and username.
 * It implements the EntityData interface.
 */
@Builder
public record FriendData(String friendFullName, String friendJob, String friendUsername) implements EntityData {
}

