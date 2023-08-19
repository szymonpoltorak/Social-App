package razepl.dev.socialappbackend.home.data;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CommentResponse(String commentAuthor,
                              String commentContent,
                              long commentId,
                              LocalDate commentDate,
                              long numOfLikes,
                              boolean isCommentLiked) {
}
