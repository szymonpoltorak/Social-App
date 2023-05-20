package razepl.dev.socialappbackend.entities.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("""
            select c
            from Comment as c
            where c.post.postId = :postId
            """)
    Page<Comment> findCommentsByPostId(long postId, Pageable pageable);
}
