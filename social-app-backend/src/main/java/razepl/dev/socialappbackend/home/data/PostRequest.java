package razepl.dev.socialappbackend.home.data;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PostRequest(String postContent, String authorUsername, LocalDate dateOfCreation) {
}
