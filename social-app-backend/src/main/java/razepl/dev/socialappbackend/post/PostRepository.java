package razepl.dev.socialappbackend.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("""
            select p from Post as p
            inner join User as u on p.user.userId = u.userId
            """)
    Page<Post> getPosts(Pageable pageable);
}
