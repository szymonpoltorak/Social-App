package razepl.dev.socialappbackend.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Getter
@NoArgsConstructor
@Entity
public class User implements ServiceUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate dateOfBirth;
    private String name;
    private String surname;
    private String email;
    private int passwordHashCode;

    public User(int id, LocalDate dateOfBirth, String name, String surname, String email, String password) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwordHashCode = password.hashCode();
    }

    @Override
    public int getAge() {
        return Period.between(LocalDate.now(), this.dateOfBirth).getYears();
    }
}
