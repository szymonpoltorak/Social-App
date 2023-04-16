package razepl.dev.socialappbackend.auth.jwt.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razepl.dev.socialappbackend.auth.jwt.JwtToken;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<JwtToken, Long> {
    Optional<JwtToken> findByToken(String jwtToken);

    @Query("select t from JwtToken as t inner join User as u on (t.user.userId = u.userId) where t.isExpired = false and t.isRevoked = false")
//    List<JwtToken> findAllByUser(User user);
    List<JwtToken> findAllByUser(Long id);
}
