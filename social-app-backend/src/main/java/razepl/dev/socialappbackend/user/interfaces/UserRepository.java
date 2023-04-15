package razepl.dev.socialappbackend.user.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import razepl.dev.socialappbackend.user.User;

import java.util.Optional;

/**
 * A repository interface for accessing and managing {@link User} entities.
 * It extends the {@link JpaRepository} interface to inherit common CRUD and pagination operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);
}
