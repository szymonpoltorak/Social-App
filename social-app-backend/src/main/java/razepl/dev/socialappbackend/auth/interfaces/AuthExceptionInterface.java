package razepl.dev.socialappbackend.auth.interfaces;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import razepl.dev.socialappbackend.auth.apicalls.ExceptionResponse;
import razepl.dev.socialappbackend.exceptions.AuthManagerInstanceException;
import razepl.dev.socialappbackend.exceptions.PasswordValidationException;

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
    ResponseEntity<ExceptionResponse> handleConstraintValidationExceptions(ConstraintViolationException exception);

    /**
     * Handles method argument validation exceptions that occur during user authentication and returns an HTTP response entity.
     *
     * @param exception the method argument not valid exception that occurred
     * @return an HTTP response entity containing an error message and status code
     */
    ResponseEntity<ExceptionResponse> handleMethodArgValidExceptions(MethodArgumentNotValidException exception);

    /**
     * Handles an exception related to password validation.
     *
     * @param exception The exception to handle.
     * @return A ResponseEntity containing an ExceptionResponse object with error message and exception class name.
     */
    ResponseEntity<ExceptionResponse> handlePasswordValidationException(PasswordValidationException exception);

    /**
     * Handles an exception related to a user not being found.
     *
     * @param exception The exception to handle.
     * @return A ResponseEntity containing an ExceptionResponse object with error message and exception class name.
     */
    ResponseEntity<ExceptionResponse> handleUserNotFoundException(UsernameNotFoundException exception);

    /**
     * Handles an exception related to an authentication manager instance.
     *
     * @param exception The exception to handle.
     * @return A ResponseEntity containing an ExceptionResponse object with error message and exception class name.
     */
    ResponseEntity<ExceptionResponse> handleAuthManagerInstanceException(AuthManagerInstanceException exception);

    /**
     * Handles exceptions related to JWT tokens.
     *
     * @param exception The exception to handle.
     * @return A ResponseEntity containing an ExceptionResponse object with error message and exception class name.
     */
    ResponseEntity<ExceptionResponse> handleTokenExceptions(IllegalArgumentException exception);
}
