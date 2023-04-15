package razepl.dev.socialappbackend.auth.interfaces;

import razepl.dev.socialappbackend.user.interfaces.UserPropertyInterface;

import java.time.LocalDate;

public interface RegisterUserRequest extends UserPropertyInterface {
    LocalDate getDateOfBirth();
}
