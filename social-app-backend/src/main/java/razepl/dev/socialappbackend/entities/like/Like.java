package razepl.dev.socialappbackend.entities.like;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.post.Post;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long likeId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
