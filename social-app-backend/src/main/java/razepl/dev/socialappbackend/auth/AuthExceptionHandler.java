package razepl.dev.socialappbackend.auth;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import razepl.dev.socialappbackend.auth.interfaces.AuthExceptionInterface;

import java.util.stream.Collectors;

import static razepl.dev.socialappbackend.auth.constants.AuthMessages.ERROR_DELIMITER;
import static razepl.dev.socialappbackend.auth.constants.AuthMessages.ERROR_FORMAT;

@Slf4j
@ControllerAdvice
public class AuthExceptionHandler implements AuthExceptionInterface {
    @Override
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<String> handleConstraintValidationExceptions(ConstraintViolationException exception) {
        String errorMessage = exception.getConstraintViolations()
                .stream()
                .map(error -> String.format(ERROR_FORMAT, error.getPropertyPath(), error.getMessage()))
                .collect(Collectors.joining(ERROR_DELIMITER));

        log.error(errorMessage);

        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<String> handleMethodArgValidExceptions(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> String.format(ERROR_FORMAT, error.getField(), error.getDefaultMessage()))
                .collect(Collectors.joining(ERROR_DELIMITER));

        log.error(errorMessage);

        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
