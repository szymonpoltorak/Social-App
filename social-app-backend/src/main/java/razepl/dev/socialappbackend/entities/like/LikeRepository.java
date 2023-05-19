package razepl.dev.socialappbackend.entities.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.post.Post;

import java.util.Optional;

/**
 * This interface provides data access methods for managing likes in the repository.
 */
public interface LikeRepository extends JpaRepository<Like, Long> {
    /**
     * Counts the number of likes for a specific post.
     *
     * @param post The post to count likes for.
     * @return The number of likes for the post.
     */
    long countByPost(Post post);

    /**
     * Retrieves a like by user and post.
     *
     * @param user The user who liked the post.
     * @param post The post that was liked.
     * @return An Optional containing the like, or empty if not found.
     */
    @Query("""
            select l
            from Like as l
            where l.post = :post and l.user = :user
            """)
    Optional<Like> findByUserAndPost(User user, Post post);
}
