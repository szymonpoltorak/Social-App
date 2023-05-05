package razepl.dev.socialappbackend.friend;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendsRepository extends JpaRepository<Friend, Long> {
}
