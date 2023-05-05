package razepl.dev.socialappbackend.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.exceptions.validators.NullChecker;
import razepl.dev.socialappbackend.home.data.UserDataRequest;
import razepl.dev.socialappbackend.home.interfaces.HomeUserInterface;
import razepl.dev.socialappbackend.home.interfaces.UserServiceInterface;

import static razepl.dev.socialappbackend.home.constants.HomeMappings.*;
import static razepl.dev.socialappbackend.home.constants.HomeMappings.JOB_MAPPING;
import static razepl.dev.socialappbackend.home.constants.ResponseMessages.SUCCESSFUL_DATA_UPDATE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = HOME_USER_MAPPING)
public class HomeUserController implements HomeUserInterface {
    private final UserServiceInterface userService;

    @Override
    @PatchMapping(value = TWITTER_MAPPING)
    public ResponseEntity<String> updateTwitterData(@RequestBody UserDataRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Updating users twitter data with: \n{}", request);

        userService.updateTwitterData(request);

        return ResponseEntity.ok(SUCCESSFUL_DATA_UPDATE);
    }

    @Override
    @PatchMapping(value = LINKEDIN_MAPPING)
    public ResponseEntity<String> updateLinkedinData(@RequestBody UserDataRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Updating users linkedin data with: \n{}", request);

        userService.updateLinkedinData(request);

        return ResponseEntity.ok(SUCCESSFUL_DATA_UPDATE);
    }

    @Override
    @PatchMapping(value = GITHUB_MAPPING)
    public ResponseEntity<String> updateGithubData(@RequestBody UserDataRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Updating users github data with: \n{}", request);

        userService.updateGithubData(request);

        return ResponseEntity.ok(SUCCESSFUL_DATA_UPDATE);
    }

    @Override
    @PatchMapping(value = LOCATION_MAPPING)
    public ResponseEntity<String> updateUsersLocation(@RequestBody UserDataRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Updating users location data with: \n{}", request);

        userService.updateUsersLocation(request);

        return ResponseEntity.ok(SUCCESSFUL_DATA_UPDATE);
    }

    @Override
    @PatchMapping(value = JOB_MAPPING)
    public ResponseEntity<String> updateUsersJob(@RequestBody UserDataRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Updating users job data with: \n{}", request);

        userService.updateUsersJob(request);

        return ResponseEntity.ok(SUCCESSFUL_DATA_UPDATE);
    }
}
