package razepl.dev.socialappbackend.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import razepl.dev.socialappbackend.user.ServiceUser;

import static razepl.dev.socialappbackend.auth.constants.AuthConstants.USER_REQUEST_PART;

public interface AuthInterface {
    ResponseEntity<String> registerUser(@RequestPart(USER_REQUEST_PART) ServiceUser user);
}
