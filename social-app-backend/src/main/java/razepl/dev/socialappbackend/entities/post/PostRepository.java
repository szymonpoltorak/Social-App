package razepl.dev.socialappbackend.entities.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * A repository interface for accessing and managing Post entities.
 * It extends the JpaRepository interface to inherit common CRUD and pagination operations.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * Retrieves a page of posts ordered by post date.
     *
     * @param pageable the pagination information
     * @return a Page containing the posts
     */
    @Query("""
            select p from Post as p
            inner join User as u on p.user.userId = u.userId
            order by p.postDate
            """)
    Page<Post> getPosts(Pageable pageable);
}
