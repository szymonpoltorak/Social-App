package razepl.dev.socialappbackend.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import razepl.dev.socialappbackend.auth.data.ExceptionResponse;
import razepl.dev.socialappbackend.exceptions.FriendNotFoundException;
import razepl.dev.socialappbackend.exceptions.PostNotFoundException;
import razepl.dev.socialappbackend.home.interfaces.HomeExceptionHandler;

@ControllerAdvice
@Slf4j
public class HomeExceptionHandlerImpl implements HomeExceptionHandler {
    @Override
    @ExceptionHandler({PostNotFoundException.class, FriendNotFoundException.class})
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(IllegalArgumentException exception) {
        String message = exception.getMessage();
        String exceptionClassName = exception.getClass().getSimpleName();
        ExceptionResponse response = ExceptionResponse
                .builder()
                .exceptionClassName(exceptionClassName)
                .errorMessage(message)
                .build();

        log.error("During work exception occurred. Here are the details: \n{}", response);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
