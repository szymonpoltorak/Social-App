package razepl.dev.socialappbackend.home.data;

import lombok.Builder;
import razepl.dev.socialappbackend.globals.EntityData;

import java.time.LocalDate;

@Builder
public record PostData(String postContent, String postAuthor, String postLocation,
                       LocalDate postDate, long numOfLikes, long numOfComments, long postId) implements EntityData {
}
