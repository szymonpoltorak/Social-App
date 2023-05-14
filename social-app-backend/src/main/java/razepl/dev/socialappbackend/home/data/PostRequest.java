package razepl.dev.socialappbackend.home.data;

import lombok.Builder;

@Builder
public record PostRequest(String postContent, String authorUsername) {
}
