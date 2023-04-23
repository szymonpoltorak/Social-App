package razepl.dev.socialappbackend.auth.apicalls;

import lombok.Builder;

@Builder
public record TokenResponse(boolean isAuthTokenValid) {
}
