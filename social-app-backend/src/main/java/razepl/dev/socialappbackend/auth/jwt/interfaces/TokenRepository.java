package razepl.dev.socialappbackend.auth.jwt.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razepl.dev.socialappbackend.auth.jwt.JwtToken;

import java.util.List;
import java.util.Optional;

/**
 * A repository for JwtTokens.
 * It extends {@link JpaRepository}.
 */
public interface TokenRepository extends JpaRepository<JwtToken, Long> {
    /**
     * Method to find token object inside database by the given token string
     * @param jwtToken token string
     * @return {@link Optional} of {@link JwtToken} instance
     */
    Optional<JwtToken> findByToken(String jwtToken);

    /**
     * Method used to return the list of all tokens that user have based on his id.
     * @param id the id of the user
     * @return List of {@link JwtToken} of the user
     */
    @Query("select t from JwtToken as t inner join User as u on (t.user.userId = u.userId) where t.isExpired = false and t.isRevoked = false")
    List<JwtToken> findAllByUser(Long id);
}
