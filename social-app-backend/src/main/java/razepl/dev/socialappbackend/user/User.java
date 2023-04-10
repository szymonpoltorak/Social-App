package razepl.dev.socialappbackend.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;

import static razepl.dev.socialappbackend.user.constants.UserValidation.*;
import static razepl.dev.socialappbackend.user.constants.UserValidationMessages.*;

/**
 * This class represents a user in the system.
 * It implements the ServiceUser interface which specifies the behavior of a user in a service.
 * The class has several annotations that specify its behavior and constraints.
 */
@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements ServiceUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = DATE_NULL_MESSAGE)
    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate dateOfBirth;

    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH, message = NAME_SIZE_MESSAGE)
    @Pattern(regexp = NAME_PATTERN, message = NAME_PATTERN_MESSAGE)
    private String name;

    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH, message = SURNAME_SIZE_MESSAGE)
    @Pattern(regexp = NAME_PATTERN, message = SURNAME_PATTERN_MESSAGE)
    private String surname;

    @Column(unique = true)
    @Email(message = EMAIL_MESSAGE)
    private String email;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = PASSWORD_SIZE_MESSAGE)
    @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_PATTERN_MESSAGE)
    private String password;

    @Override
    public final int getAge() {
        log.info("Getting age of user : {}", id);

        return Period.between(LocalDate.now(), this.dateOfBirth).getYears();
    }
}
