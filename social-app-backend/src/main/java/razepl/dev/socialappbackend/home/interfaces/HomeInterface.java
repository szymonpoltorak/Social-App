package razepl.dev.socialappbackend.home.interfaces;

import org.springframework.http.ResponseEntity;
import razepl.dev.socialappbackend.home.data.UserData;

public interface HomeInterface {
    ResponseEntity<UserData> getUserData(String username);
}
