package razepl.dev.socialappbackend.search.data;

import lombok.Builder;

@Builder
public record UserSearchData(String username, String job, String fullName, boolean isUsersFriend) {
}
