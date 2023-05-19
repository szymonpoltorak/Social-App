package razepl.dev.socialappbackend.home.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.auth.apicalls.ExceptionResponse;

/**
 * Interface for handling exceptions related to home operations.
 */
public interface HomeHandler {
    /**
     * Handles the exception of type IllegalArgumentException and returns an ExceptionResponse.
     *
     * @param exception the IllegalArgumentException to handle
     * @return a ResponseEntity containing the ExceptionResponse
     */
    ResponseEntity<ExceptionResponse> handleNotFoundException(IllegalArgumentException exception);
}
