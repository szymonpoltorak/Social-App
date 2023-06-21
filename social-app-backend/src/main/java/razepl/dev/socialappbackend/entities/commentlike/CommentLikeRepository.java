package razepl.dev.socialappbackend.entities.commentlike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razepl.dev.socialappbackend.entities.comment.Comment;
import razepl.dev.socialappbackend.entities.user.User;

import java.util.Optional;

/**
 * Repository interface for accessing and managing CommentLike entities.
 * Extends the JpaRepository interface to inherit common CRUD operations.
 */
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    /**
     * Counts the number of likes for a specific comment.
     *
     * @param comment - The comment to count likes for.
     * @return The number of likes for the specified comment.
     */
    long countByComment(Comment comment);

    /**
     * Retrieves a like for a specific comment and user.
     *
     * @param user    - The user who liked the comment.
     * @param comment - The comment to find the like for.
     * @return An Optional containing the like for the specified comment and user, or empty if not found.
     */
    @Query("""
            select l
            from CommentLike as l
            where l.comment = :comment and l.user = :user
            """)
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
