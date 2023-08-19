package razepl.dev.socialappbackend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import razepl.dev.socialappbackend.auth.data.RegisterRequest;
import razepl.dev.socialappbackend.exceptions.JsonMapperException;

import java.time.LocalDate;

public class AuthTestUtil {
    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(obj);
        } catch (NullPointerException | IllegalStateException | JsonProcessingException exception) {
            throw new JsonMapperException(exception.getMessage());
        }
    }

    private static RegisterRequest buildUserRequest(LocalDate dateOfBirth, String name, String surname, String email, String password) {
        return RegisterRequest
                .builder()
                .dateOfBirth(dateOfBirth)
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .build();
    }

    public static RegisterRequest createUserForRegister(String password) {
        String name = "Adam";
        String surname = "Kowalski";
        String email = "andrzej@gmail.com";
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);

        return buildUserRequest(dateOfBirth, name, surname, email, password);
    }
}
