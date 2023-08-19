package razepl.dev.socialappbackend.auth.data;

import lombok.Builder;

@Builder
public record TokenRequest(String authToken) {
}
