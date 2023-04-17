package razepl.dev.socialappbackend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import razepl.dev.socialappbackend.auth.apicalls.RegisterRequest;
import razepl.dev.socialappbackend.auth.interfaces.RegisterUserRequest;
import razepl.dev.socialappbackend.exceptions.JsonMapperException;

import java.time.LocalDate;

public class AuthTestUtil {
    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(obj);
        } catch (Exception exception) {
            throw new JsonMapperException(exception.getMessage());
        }
    }

    public static RegisterUserRequest buildUserRequest(LocalDate dateOfBirth, String name, String surname, String email, String password) {
        return RegisterRequest
                .builder()
                .dateOfBirth(dateOfBirth)
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .build();
    }
}
