package razepl.dev.socialappbackend.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.user.ServiceUser;

@Slf4j
@RestController
@RequestMapping("/auth/")
public class AuthController implements AuthInterface {
    @Override
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> registerUser(ServiceUser user) {
        return ResponseEntity.ok("User has been successfully registered!");
    }
}
