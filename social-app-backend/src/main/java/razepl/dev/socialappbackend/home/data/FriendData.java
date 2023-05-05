package razepl.dev.socialappbackend.home.data;

import lombok.Builder;

@Builder
public record FriendData(String fullName, String job, String friendUsername) {
}
