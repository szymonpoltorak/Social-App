package razepl.dev.socialappbackend.entities.user.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razepl.dev.socialappbackend.entities.user.User;

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

    /**
     * Finds a user by authentication token.
     *
     * @param authToken the authentication token of the user to find
     * @return an Optional containing the user associated with the given authentication token, or empty if not found
     */
    @Query("select u from User as u inner join JwtToken as t on u.userId = t.user.userId where t.token = :authToken")
    Optional<User> findUserByToken(String authToken);

    @Query("""
            select u
            from User as u
            where concat(u.name, ' ', u.surname) like :pattern% and u != :user
            """)
    Page<User> findAllByPattern(String pattern, Pageable pageable, User user);
}
