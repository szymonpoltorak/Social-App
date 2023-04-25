package razepl.dev.socialappbackend.auth.apicalls;

import lombok.Builder;

@Builder
public record TokenRequest(String authToken) {
}
