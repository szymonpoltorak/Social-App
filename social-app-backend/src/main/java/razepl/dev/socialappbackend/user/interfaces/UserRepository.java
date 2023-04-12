package razepl.dev.socialappbackend.user.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import razepl.dev.socialappbackend.user.User;

/**
 * A repository interface for accessing and managing {@link User} entities.
 * It extends the {@link JpaRepository} interface to inherit common CRUD and pagination operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u.name from Users as u where u.name = :name")
    String findByName(@Param("name") String name);
}
