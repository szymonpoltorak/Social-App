package razepl.dev.socialappbackend.home.data;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PostData(String postContent, String postAuthor, String postLocation, LocalDate postDate) {
}
