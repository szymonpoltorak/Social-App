package razepl.dev.socialappbackend.home.data;

import lombok.Builder;

@Builder
public record LikeData(long numOfLikes, boolean isLiked) {
}
