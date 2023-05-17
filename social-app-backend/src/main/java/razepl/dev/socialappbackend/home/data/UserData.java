package razepl.dev.socialappbackend.home.data;

import lombok.Builder;

@Builder
public record UserData(String fullName, long numOfFriends, String location, String job,
                       String twitter, String linkedin, String github) {
}
