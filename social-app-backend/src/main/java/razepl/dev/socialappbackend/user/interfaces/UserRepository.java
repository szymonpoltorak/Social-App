package razepl.dev.socialappbackend.user.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository interface for accessing and managing {@link ServiceUser} entities.
 * It extends the {@link JpaRepository} interface to inherit common CRUD and pagination operations.
 */
public interface UserRepository extends JpaRepository<ServiceUser, Long> {
}
