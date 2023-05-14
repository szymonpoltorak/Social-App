package razepl.dev.socialappbackend.post;

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
import razepl.dev.socialappbackend.globals.DataBuilder;
import razepl.dev.socialappbackend.home.data.PostData;
import razepl.dev.socialappbackend.user.User;

import java.time.LocalDate;

import static razepl.dev.socialappbackend.user.constants.UserValidation.DATE_PATTERN;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Posts")
public class Post implements DataBuilder<PostData> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    @Size(min = 1, max = 120)
    private String postContent;

    private long numOfLikes;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate postDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public final void incrementLikeCounter() {
        numOfLikes++;
    }

    @Override
    public final PostData buildData() {
        return PostData
                .builder()
                .postAuthor(user.getFullName())
                .postContent(postContent)
                .postDate(postDate)
                .numOfLikes(numOfLikes)
                .postId(postId)
                .build();
    }
}
