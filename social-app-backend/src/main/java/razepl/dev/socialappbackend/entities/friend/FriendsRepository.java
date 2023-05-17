package razepl.dev.socialappbackend.entities.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razepl.dev.socialappbackend.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface FriendsRepository extends JpaRepository<Friend, Long> {
    long countFriendByUser(User user);

    @Query("""
            select f
            from Friends as f
            where f.user = :user
            limit 12
            """)
    Optional<List<Friend>> findFriendsByUser(User user);

    Optional<Friend> findByFriendUsernameAndUser(String friendUsername, User user);
}
