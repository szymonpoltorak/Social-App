package razepl.dev.socialappbackend.entities.comment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import razepl.dev.socialappbackend.entities.post.Post;
import razepl.dev.socialappbackend.entities.user.User;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "Comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String commentContent;

    private LocalDate commentDate;
}
