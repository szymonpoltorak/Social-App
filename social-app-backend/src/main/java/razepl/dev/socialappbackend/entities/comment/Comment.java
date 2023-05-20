package razepl.dev.socialappbackend.entities.comment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import razepl.dev.socialappbackend.entities.post.Post;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.globals.DataBuilder;
import razepl.dev.socialappbackend.home.data.CommentData;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "Comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements DataBuilder<CommentData> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String commentContent;

    private LocalDate commentDate;

    @Override
    public CommentData buildData() {
        return CommentData
                .builder()
                .commentAuthor(user.getFullName())
                .commentContent(commentContent)
                .commentDate(commentDate)
                .commentId(commentId)
                .build();
    }
}
