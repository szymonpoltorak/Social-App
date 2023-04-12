package razepl.dev.socialappbackend.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import razepl.dev.socialappbackend.user.interfaces.ServiceUser;

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
@Setter
@NoArgsConstructor
@Entity(name = "Users")
public class User implements ServiceUser {
    @NotNull(message = DATE_NULL_MESSAGE)
    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    @NotNull(message = NAME_NULL_MESSAGE)
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH, message = NAME_SIZE_MESSAGE)
    @Pattern(regexp = NAME_PATTERN, message = NAME_PATTERN_MESSAGE)
    private String name;

    @NotNull(message = SURNAME_NULL_MESSAGE)
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH, message = SURNAME_SIZE_MESSAGE)
    @Pattern(regexp = NAME_PATTERN, message = SURNAME_PATTERN_MESSAGE)
    private String surname;

    @NotNull(message = EMAIL_NULL_MESSAGE)
    @Column(unique = true)
    @Email(message = EMAIL_MESSAGE)
    private String email;

    @NotNull(message = PASSWORD_NULL_MESSAGE)
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = PASSWORD_SIZE_MESSAGE)
    @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_PATTERN_MESSAGE)
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public User(LocalDate dateOfBirth, String name, String surname, String email, String password) {
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    @Override
    public final int getAge() {
        log.info("Getting age of user : {}", id);

        return Period.between(LocalDate.now(), this.dateOfBirth).getYears();
    }
}
