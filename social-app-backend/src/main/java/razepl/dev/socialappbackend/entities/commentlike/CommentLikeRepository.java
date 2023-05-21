package razepl.dev.socialappbackend.entities.commentlike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razepl.dev.socialappbackend.entities.comment.Comment;
import razepl.dev.socialappbackend.entities.user.User;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    long countByComment(Comment comment);

    @Query("""
            select l
            from CommentLike as l
            where l.comment = :comment and l.user = :user
            """)
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
