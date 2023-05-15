package razepl.dev.socialappbackend.friend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import razepl.dev.socialappbackend.globals.DataBuilder;
import razepl.dev.socialappbackend.home.data.FriendData;
import razepl.dev.socialappbackend.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "Friends")
public class Friend implements DataBuilder<FriendData> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long friendshipId;

    private String friendName;

    private String friendJob;

    private String friendUsername;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public FriendData buildData() {
        return FriendData
                .builder()
                .friendFullName(friendName)
                .friendUsername(friendUsername)
                .friendJob(friendJob == null ? "" : friendJob)
                .build();
    }
}
