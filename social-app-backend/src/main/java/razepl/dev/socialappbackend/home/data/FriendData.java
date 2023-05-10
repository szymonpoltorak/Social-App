package razepl.dev.socialappbackend.home.data;

import lombok.Builder;
import razepl.dev.socialappbackend.globals.EntityData;

@Builder
public record FriendData(String friendFullName, String friendJob, String friendUsername) implements EntityData {
}
