package razepl.dev.socialappbackend.home.data;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CommentData(String commentAuthor,
                          String commentContent,
                          long commentId,
                          LocalDate commentDate,
                          long numOfLikes,
                          boolean isCommentLiked) {
}
