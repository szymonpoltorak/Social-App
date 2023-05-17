package razepl.dev.socialappbackend.home.data;

import lombok.Builder;

/**
 * This record represents user data, including the full name, number of friends, location, job,
 * Twitter username, LinkedIn username, and GitHub username.
 */
@Builder
public record UserData(String fullName, long numOfFriends, String location, String job,
                       String twitter, String linkedin, String github) {
}

