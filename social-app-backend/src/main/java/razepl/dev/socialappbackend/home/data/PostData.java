package razepl.dev.socialappbackend.home.data;

import lombok.Builder;
import razepl.dev.socialappbackend.globals.EntityData;

import java.time.LocalDate;

/**
 * This record represents post data, including the post content, author, date, username,
 * number of likes, number of comments, post ID, whether the post is liked,
 * and whether the user is in the author's friend list.
 * It implements the EntityData interface.
 */
@Builder
public record PostData(String postContent, String postAuthor, LocalDate postDate, String username,
                       long numOfLikes, long numOfComments, long postId, boolean isPostLiked,
                       boolean isUserInFriends) implements EntityData {
}

