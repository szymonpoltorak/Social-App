package razepl.dev.socialappbackend.auth.apicalls;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private String errorMessage;
    private String exceptionClassName;
}
