package razepl.dev.socialappbackend.home.data;

import lombok.Builder;

/**
 * This record represents friend data, including the friend's full name, job, and username.
 * It implements the EntityData interface.
 */
@Builder
public record FriendResponse(String friendFullName, String friendJob, String friendUsername) {
}

