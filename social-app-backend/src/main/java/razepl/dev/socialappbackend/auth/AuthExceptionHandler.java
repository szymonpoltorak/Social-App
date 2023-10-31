package razepl.dev.socialappbackend.auth;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import razepl.dev.socialappbackend.auth.data.ExceptionResponse;
import razepl.dev.socialappbackend.auth.data.TokenResponse;
import razepl.dev.socialappbackend.auth.interfaces.AuthExceptionInterface;
import razepl.dev.socialappbackend.exceptions.AuthManagerInstanceException;
import razepl.dev.socialappbackend.exceptions.InvalidTokenException;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;
import razepl.dev.socialappbackend.exceptions.PasswordValidationException;
import razepl.dev.socialappbackend.exceptions.TokenDoesNotExistException;
import razepl.dev.socialappbackend.exceptions.TokensUserNotFoundException;
import razepl.dev.socialappbackend.exceptions.UserAlreadyExistsException;

import java.util.stream.Collectors;

import static razepl.dev.socialappbackend.auth.constants.AuthMessages.ERROR_DELIMITER;
import static razepl.dev.socialappbackend.auth.constants.AuthMessages.ERROR_FORMAT;

/**
 * Class created to handle various exceptions that can be thrown in auth endpoints.
 * It implements {@link AuthExceptionInterface}.
 */
@Slf4j
@ControllerAdvice
public class AuthExceptionHandler implements AuthExceptionInterface {
    @Override
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ExceptionResponse> handleConstraintValidationExceptions(ConstraintViolationException exception) {
        String className = exception.getClass().getSimpleName();
        String errorMessage = exception.getConstraintViolations()
                .stream()
                .map(error -> String.format(ERROR_FORMAT, error.getPropertyPath(), error.getMessage()))
                .collect(Collectors.joining(ERROR_DELIMITER));

        log.error("Exception class name : {}\nError message : {}", className, errorMessage);

        return new ResponseEntity<>(buildResponse(errorMessage, className), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ExceptionResponse> handleMethodArgValidExceptions(MethodArgumentNotValidException exception) {
        String className = exception.getClass().getSimpleName();
        String errorMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> String.format(ERROR_FORMAT, error.getField(), error.getDefaultMessage()))
                .collect(Collectors.joining(ERROR_DELIMITER));

        log.error("Exception class name : {}\nError message : {}", className, errorMessage);

        return new ResponseEntity<>(buildResponse(errorMessage, className), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    @ExceptionHandler(PasswordValidationException.class)
    public final ResponseEntity<ExceptionResponse> handlePasswordValidationException(ValidationException exception) {
        return buildResponseEntity(exception, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UsernameNotFoundException exception) {
        return buildResponseEntity(exception, HttpStatus.NOT_FOUND);
    }

    @Override
    @ExceptionHandler(AuthManagerInstanceException.class)
    public final ResponseEntity<ExceptionResponse> handleAuthManagerInstanceException(InstantiationException exception) {
        return buildResponseEntity(exception, HttpStatus.FAILED_DEPENDENCY);
    }

    @Override
    @ExceptionHandler({InvalidTokenException.class, TokenDoesNotExistException.class, NullArgumentException.class})
    public final ResponseEntity<ExceptionResponse> handleTokenExceptions(IllegalArgumentException exception) {
        return buildResponseEntity(exception, HttpStatus.UNAUTHORIZED);
    }

    @Override
    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<ExceptionResponse> handleUserExistException(IllegalStateException exception) {
        return buildResponseEntity(exception, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    @ExceptionHandler(TokensUserNotFoundException.class)
    public final ResponseEntity<TokenResponse> handleTokenExceptions(IllegalStateException exception) {
        String className = exception.getClass().getSimpleName();
        TokenResponse response = TokenResponse.builder().isAuthTokenValid(false).build();

        log.error("Exception class name : {}\nError message : {}", className, exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<ExceptionResponse> buildResponseEntity(Exception exception, HttpStatus status) {
        String errorMessage = exception.getMessage();
        String className = exception.getClass().getSimpleName();

        log.error("Exception class name : {}\nError message : {}", className, errorMessage);

        return new ResponseEntity<>(buildResponse(errorMessage, className), status);
    }

    private ExceptionResponse buildResponse(String errorMessage, String exceptionClassName) {
        return ExceptionResponse.builder()
                .errorMessage(errorMessage)
                .exceptionClassName(exceptionClassName)
                .build();
    }
}
