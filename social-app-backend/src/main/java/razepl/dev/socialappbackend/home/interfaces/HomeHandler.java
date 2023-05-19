package razepl.dev.socialappbackend.home.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.auth.apicalls.ExceptionResponse;

public interface HomeHandler {
    ResponseEntity<ExceptionResponse> handleNotFoundException(IllegalArgumentException exception);
}
