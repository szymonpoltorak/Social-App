package razepl.dev.socialappbackend.auth.jwt.interfaces;

/**
 * Interface for JwtToken entity class.
 */
public interface Token {
    /**
     * Method used to return JWT token.
     *
     * @return String representation of JWT token.
     */
    String getToken();
}
