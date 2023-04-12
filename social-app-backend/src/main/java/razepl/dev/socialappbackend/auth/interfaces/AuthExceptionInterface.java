package razepl.dev.socialappbackend.auth.interfaces;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * The AuthExceptionInterface interface defines a methods for handling errors in Authentication.
 */
public interface AuthExceptionInterface {

    /**
     * Handles validation exceptions that occur during user authentication and returns an HTTP response entity.
     *
     * @param exception the constraint violation exception that occurred
     * @return an HTTP response entity containing an error message and status code
     */
    ResponseEntity<String> handleConstraintValidationExceptions(ConstraintViolationException exception);

    /**
     * Handles method argument validation exceptions that occur during user authentication and returns an HTTP response entity.
     *
     * @param exception the method argument not valid exception that occurred
     * @return an HTTP response entity containing an error message and status code
     */
    ResponseEntity<String> handleMethodArgValidExceptions(MethodArgumentNotValidException exception);
}
