package razepl.dev.socialappbackend.user.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import razepl.dev.socialappbackend.user.User;

import java.util.Optional;

/**
 * A repository interface for accessing and managing {@link User} entities.
 * It extends the {@link JpaRepository} interface to inherit common CRUD and pagination operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by email address.
     *
     * @param email the email address of the user to find
     * @return an {@link Optional} containing the user with the given email address, or empty if not found
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds a user by name.
     *
     * @param name the name of the user to find
     * @return an {@link Optional} containing the user with the given name, or empty if not found
     */
    Optional<User> findByName(String name);
}
