package razepl.dev.socialappbackend.auth.data;

import lombok.Builder;

/**
 * The ExceptionResponse class is a model class representing an exception response.
 * This class includes an error message and the name of the exception class.
 */
@Builder
public record ExceptionResponse(String errorMessage, String exceptionClassName) {
}