package razepl.dev.socialappbackend.entities.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razepl.dev.socialappbackend.entities.user.User;

import java.util.List;
import java.util.Optional;

/**
 * A repository interface for accessing and managing Friend entities.
 * It extends the JpaRepository interface to inherit common CRUD operations.
 */
public interface FriendsRepository extends JpaRepository<Friend, Long> {
    /**
     * Counts the number of friends for a specific user.
     *
     * @param user the user to count friends for
     * @return the number of friends for the user
     */
    long countFriendByUser(User user);

    /**
     * Finds friends for a specific user.
     *
     * @param user the user to find friends for
     * @return an Optional containing the list of friends for the user, limited to 12, or empty if not found
     */
    @Query("""
            select f
            from Friends as f
            where f.user = :user
            limit 12
            """)
    Optional<List<Friend>> findFriendsByUser(User user);

    /**
     * Finds a friend by friend username and user.
     *
     * @param friendUsername the friend's username
     * @param user           the user
     * @return an Optional containing the friend with the given username and user, or empty if not found
     */
    Optional<Friend> findByFriendUsernameAndUser(String friendUsername, User user);
}
