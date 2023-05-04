package razepl.dev.socialappbackend.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.home.data.UserData;
import razepl.dev.socialappbackend.home.interfaces.HomeInterface;
import razepl.dev.socialappbackend.home.interfaces.HomeServiceInterface;

import static razepl.dev.socialappbackend.home.constants.HomeMappings.HOME_MAPPING;
import static razepl.dev.socialappbackend.home.constants.HomeMappings.USERDATA_MAPPING;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = HOME_MAPPING)
public class HomeController implements HomeInterface {
    private final HomeServiceInterface homeService;

    @Override
    @GetMapping(value = USERDATA_MAPPING)
    public final ResponseEntity<UserData> getUserData(@RequestParam String username) {
        return ResponseEntity.ok(homeService.buildUserDataFromDb(username));
    }
}
