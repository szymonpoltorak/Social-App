package razepl.dev.socialappbackend.home.data;

import lombok.Builder;

@Builder
public record UserDataRequest(String username, String updateData) {
}
