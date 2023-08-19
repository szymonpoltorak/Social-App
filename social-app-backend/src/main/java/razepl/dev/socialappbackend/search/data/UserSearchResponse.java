package razepl.dev.socialappbackend.search.data;

import lombok.Builder;

@Builder
public record UserSearchResponse(String username, String job, String fullName, boolean isUsersFriend) {
}
