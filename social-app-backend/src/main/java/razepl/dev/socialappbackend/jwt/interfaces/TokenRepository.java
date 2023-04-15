package razepl.dev.socialappbackend.jwt.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import razepl.dev.socialappbackend.jwt.JwtToken;

public interface TokenRepository extends JpaRepository<JwtToken, Long> {
}
