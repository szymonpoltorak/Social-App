package razepl.dev.socialappbackend.entities.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razepl.dev.socialappbackend.entities.post.Post;

/**
 * Repository interface for accessing and managing Comment entities.
 * Extends the JpaRepository interface to inherit common CRUD and pagination operations.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * Retrieves a page of comments for a specific post, sorted by comment date.
     *
     * @param postId - The ID of the post to retrieve comments for.
     * @param pageable - The pageable object specifying the page number, size, and sort criteria.
     * @return A page of comments for the specified post.
     */
    @Query("""
            select c
            from Comment as c
            where c.post.postId = :postId
            order by c.commentDate
            """)
    Page<Comment> findCommentsByPostId(long postId, Pageable pageable);

    /**
     * Counts the number of comments associated with a specific post.
     *
     * @param post - The post to count comments for.
     * @return The number of comments for the specified post.
     */
    long countCommentsByPost(Post post);
}
