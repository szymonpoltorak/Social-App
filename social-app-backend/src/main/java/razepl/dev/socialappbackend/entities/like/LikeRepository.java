package razepl.dev.socialappbackend.entities.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.post.Post;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    long countByPost(Post post);

    @Query("""
            select l
            from Like as l
            where l.post = :post and l.user = :user
            """)
    Optional<Like> findByUserAndPost(User user, Post post);
}
