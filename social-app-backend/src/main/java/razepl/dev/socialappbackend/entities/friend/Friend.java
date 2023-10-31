package razepl.dev.socialappbackend.entities.friend;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.FriendResponse;

/**
 * Class that represents Friend entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "Friends")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long friendshipId;

    private String friendName;

    private String friendJob;

    private String friendUsername;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public final FriendResponse buildData() {
        return FriendResponse
                .builder()
                .friendFullName(friendName)
                .friendUsername(friendUsername)
                .friendJob(friendJob == null ? "" : friendJob)
                .build();
    }
}
