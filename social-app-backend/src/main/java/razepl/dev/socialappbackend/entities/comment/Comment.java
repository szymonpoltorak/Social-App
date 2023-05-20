package razepl.dev.socialappbackend.entities.comment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import razepl.dev.socialappbackend.entities.post.Post;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.globals.DataBuilder;
import razepl.dev.socialappbackend.home.data.CommentData;

import java.time.LocalDate;

import static razepl.dev.socialappbackend.entities.user.constants.UserValidation.DATE_PATTERN;

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

    @Size(min = 1, max = 120)
    private String commentContent;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
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
