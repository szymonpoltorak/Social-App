package razepl.dev.socialappbackend.home;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.exceptions.validators.NullChecker;
import razepl.dev.socialappbackend.home.data.DataResponse;
import razepl.dev.socialappbackend.home.data.FriendUserRequest;
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
@Tag(name = "User details editing at home site")
public class HomeUserController implements HomeUserInterface {
    private final UserServiceInterface userService;

    @Override
    @PatchMapping(value = ADD_FRIEND_MAPPING)
    public final ResponseEntity<DataResponse> addToUsersFriends(@RequestBody FriendUserRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Adding friend: {}, for user : {}", request.friendsUsername(), request.username());

        userService.addFriendToUser(request);

        return ResponseEntity.ok(new DataResponse("Successfully add friend to user!"));
    }

    @Override
    @PatchMapping(value = REMOVE_FRIEND_MAPPING)
    public final ResponseEntity<DataResponse> removeFromUsersFriends(@RequestBody FriendUserRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Removing friend: {}, from user : {}", request.friendsUsername(), request.username());

        userService.removeFriendFromUser(request);

        return ResponseEntity.ok(new DataResponse("Successfully removed friend from user!"));
    }

    @Override
    @PatchMapping(value = TWITTER_MAPPING)
    public final ResponseEntity<DataResponse> updateTwitterData(@RequestBody UserDataRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Updating users twitter data with: \n{}", request);

        userService.updateTwitterData(request);

        return ResponseEntity.ok(new DataResponse(SUCCESSFUL_DATA_UPDATE));
    }

    @Override
    @PatchMapping(value = LINKEDIN_MAPPING)
    public final ResponseEntity<DataResponse> updateLinkedinData(@RequestBody UserDataRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Updating users linkedin data with: \n{}", request);

        userService.updateLinkedinData(request);

        return ResponseEntity.ok(new DataResponse(SUCCESSFUL_DATA_UPDATE));
    }

    @Override
    @PatchMapping(value = GITHUB_MAPPING)
    public final ResponseEntity<DataResponse> updateGithubData(@RequestBody UserDataRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Updating users github data with: \n{}", request);

        userService.updateGithubData(request);

        return ResponseEntity.ok(new DataResponse(SUCCESSFUL_DATA_UPDATE));
    }

    @Override
    @PatchMapping(value = LOCATION_MAPPING)
    public final ResponseEntity<DataResponse> updateUsersLocation(@RequestBody UserDataRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Updating users location data with: \n{}", request);

        userService.updateUsersLocation(request);

        return ResponseEntity.ok(new DataResponse(SUCCESSFUL_DATA_UPDATE));
    }

    @Override
    @PatchMapping(value = JOB_MAPPING)
    public final ResponseEntity<DataResponse> updateUsersJob(@RequestBody UserDataRequest request) {
        NullChecker.throwAppropriateException(request);

        log.info("Updating users friendJob data with: \n{}", request);

        userService.updateUsersJob(request);

        return ResponseEntity.ok(new DataResponse(SUCCESSFUL_DATA_UPDATE));
    }
}
