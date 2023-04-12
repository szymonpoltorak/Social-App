package razepl.dev.socialappbackend.auth.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import razepl.dev.socialappbackend.user.User;

/**
 * An interface that defines the methods for authentication services.
 */
public interface AuthInterface {
    /**
     * Registers a new user with the given details.
     * @param user the user to register
     * @return a response entity with a status code and a message
     */
    ResponseEntity<String> registerUser(@RequestBody User user);
}
