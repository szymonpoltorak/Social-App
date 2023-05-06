package razepl.dev.socialappbackend.home;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.exceptions.validators.NullChecker;
import razepl.dev.socialappbackend.home.data.FriendData;
import razepl.dev.socialappbackend.home.data.UserData;
import razepl.dev.socialappbackend.home.interfaces.HomeInterface;
import razepl.dev.socialappbackend.home.interfaces.HomeServiceInterface;

import java.util.List;

import static razepl.dev.socialappbackend.home.constants.HomeMappings.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = HOME_MAPPING)
@Tag(name = "Home site data provider")
public class HomeController implements HomeInterface {
    private final HomeServiceInterface homeService;

    @Override
    @GetMapping(value = USERDATA_MAPPING)
    public final ResponseEntity<UserData> getUserData(@RequestParam String username) {
        NullChecker.throwAppropriateException(username);

        log.info("Getting userdata for user {}", username);

        return ResponseEntity.ok(homeService.buildUserDataFromDb(username));
    }

    @Override
    @GetMapping(value = FRIENDS_LIST_MAPPING)
    public final ResponseEntity<List<FriendData>> getFriendsList(@RequestParam String username) {
        NullChecker.throwAppropriateException(username);

        log.info("Finding list of users for {}", username);

        return ResponseEntity.ok(homeService.buildUsersFriendList(username));
    }
}
