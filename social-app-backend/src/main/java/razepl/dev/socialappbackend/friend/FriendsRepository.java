package razepl.dev.socialappbackend.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import razepl.dev.socialappbackend.user.User;

import java.util.List;
import java.util.Optional;

public interface FriendsRepository extends JpaRepository<Friend, Long> {
    long countFriendByUser(User user);

    Optional<List<Friend>> findAllByUser(User user);
}
