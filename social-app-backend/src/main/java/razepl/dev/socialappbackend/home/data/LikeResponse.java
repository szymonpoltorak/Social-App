package razepl.dev.socialappbackend.home.data;

import lombok.Builder;

@Builder
public record LikeResponse(long numOfLikes, boolean isLiked) {
}
