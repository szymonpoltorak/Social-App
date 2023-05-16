package razepl.dev.socialappbackend.home;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.exceptions.validators.NullChecker;
import razepl.dev.socialappbackend.home.data.DataResponse;
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
    public final ResponseEntity<DataResponse> addToUsersFriends(@RequestBody String friendsUsername,
                                                                @AuthenticationPrincipal User user) {
        NullChecker.throwAppropriateException(friendsUsername);

        log.info("Adding friend: {}, for user : {}", friendsUsername, user);

        userService.addFriendToUser(friendsUsername, user);

        return ResponseEntity.ok(new DataResponse("Successfully add friend to user!"));
    }

    @Override
    @PatchMapping(value = REMOVE_FRIEND_MAPPING)
    public final ResponseEntity<DataResponse> removeFromUsersFriends(@RequestBody String friendsUsername,
                                                                     @AuthenticationPrincipal User user) {
        NullChecker.throwAppropriateException(friendsUsername);

        log.info("Removing friend: {}, from user : {}", friendsUsername, user);

        userService.removeFriendFromUser(friendsUsername, user);

        return ResponseEntity.ok(new DataResponse("Successfully removed friend from user!"));
    }

    @Override
    @PatchMapping(value = TWITTER_MAPPING)
    public final ResponseEntity<DataResponse> updateTwitterData(@RequestBody String updateData,
                                                                @AuthenticationPrincipal User user) {
        NullChecker.throwAppropriateException(updateData);

        log.info("Updating users twitter data with: \n{}", updateData);

        userService.updateTwitterData(updateData, user);

        return ResponseEntity.ok(new DataResponse(SUCCESSFUL_DATA_UPDATE));
    }

    @Override
    @PatchMapping(value = LINKEDIN_MAPPING)
    public final ResponseEntity<DataResponse> updateLinkedinData(@RequestBody String updateData,
                                                                 @AuthenticationPrincipal User user) {
        NullChecker.throwAppropriateException(updateData);

        log.info("Updating users linkedin data with: \n{}", updateData);

        userService.updateLinkedinData(updateData, user);

        return ResponseEntity.ok(new DataResponse(SUCCESSFUL_DATA_UPDATE));
    }

    @Override
    @PatchMapping(value = GITHUB_MAPPING)
    public final ResponseEntity<DataResponse> updateGithubData(@RequestBody String updateData,
                                                               @AuthenticationPrincipal User user) {
        NullChecker.throwAppropriateException(updateData);

        log.info("Updating users github data with: \n{}", updateData);

        userService.updateGithubData(updateData, user);

        return ResponseEntity.ok(new DataResponse(SUCCESSFUL_DATA_UPDATE));
    }

    @Override
    @PatchMapping(value = LOCATION_MAPPING)
    public final ResponseEntity<DataResponse> updateUsersLocation(@RequestBody String updateData,
                                                                  @AuthenticationPrincipal User user) {
        NullChecker.throwAppropriateException(updateData);

        log.info("Updating users location data with: \n{}", updateData);

        userService.updateUsersLocation(updateData, user);

        return ResponseEntity.ok(new DataResponse(SUCCESSFUL_DATA_UPDATE));
    }

    @Override
    @PatchMapping(value = JOB_MAPPING)
    public final ResponseEntity<DataResponse> updateUsersJob(@RequestBody String updateData,
                                                             @AuthenticationPrincipal User user) {
        NullChecker.throwAppropriateException(updateData);

        log.info("Updating users friendJob data with: \n{}", updateData);

        userService.updateUsersJob(updateData, user);

        return ResponseEntity.ok(new DataResponse(SUCCESSFUL_DATA_UPDATE));
    }
}
