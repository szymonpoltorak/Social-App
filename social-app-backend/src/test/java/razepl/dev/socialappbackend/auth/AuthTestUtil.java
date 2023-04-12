package razepl.dev.socialappbackend.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import razepl.dev.socialappbackend.exceptions.JsonMapperException;

class AuthTestUtil {
    static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception exception) {
            throw new JsonMapperException(exception.getMessage());
        }
    }
}
