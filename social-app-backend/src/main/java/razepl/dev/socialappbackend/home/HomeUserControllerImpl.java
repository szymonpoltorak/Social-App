package razepl.dev.socialappbackend.home;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.home.data.DataResponse;
import razepl.dev.socialappbackend.home.interfaces.HomeUserController;
import razepl.dev.socialappbackend.home.interfaces.UserService;

import static razepl.dev.socialappbackend.home.constants.HomeMappings.ADD_FRIEND_MAPPING;
import static razepl.dev.socialappbackend.home.constants.HomeMappings.GITHUB_MAPPING;
import static razepl.dev.socialappbackend.home.constants.HomeMappings.HOME_USER_MAPPING;
import static razepl.dev.socialappbackend.home.constants.HomeMappings.JOB_MAPPING;
import static razepl.dev.socialappbackend.home.constants.HomeMappings.LINKEDIN_MAPPING;
import static razepl.dev.socialappbackend.home.constants.HomeMappings.LOCATION_MAPPING;
import static razepl.dev.socialappbackend.home.constants.HomeMappings.REMOVE_FRIEND_MAPPING;
import static razepl.dev.socialappbackend.home.constants.HomeMappings.TWITTER_MAPPING;
import static razepl.dev.socialappbackend.home.constants.ResponseMessages.SUCCESSFUL_DATA_UPDATE;

/**
 * Controller for /api/home/user endpoints.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = HOME_USER_MAPPING)
@Tag(name = "User details editing at home site")
public class HomeUserControllerImpl implements HomeUserController {
    private final UserService userService;

    @Override
    @PatchMapping(value = ADD_FRIEND_MAPPING)
    public final DataResponse addToUsersFriends(@RequestParam String friendsUsername,
                                                @AuthenticationPrincipal User user) {
        log.info("Adding friend: {}, for user : {}", friendsUsername, user);

        userService.addFriendToUser(friendsUsername, user);

        return new DataResponse("Successfully add friend to user!");
    }

    @Override
    @PatchMapping(value = REMOVE_FRIEND_MAPPING)
    public final DataResponse removeFromUsersFriends(@RequestParam String friendsUsername,
                                                     @AuthenticationPrincipal User user) {
        log.info("Removing friend: {}, from user : {}", friendsUsername, user);

        userService.removeFriendFromUser(friendsUsername, user);

        return new DataResponse("Successfully removed friend from user!");
    }

    @Override
    @PatchMapping(value = TWITTER_MAPPING)
    public final DataResponse updateTwitterData(@RequestParam String updateData,
                                                @AuthenticationPrincipal User user) {
        log.info("Updating users twitter data with: \n{}", updateData);

        userService.updateTwitterData(updateData, user);

        return new DataResponse(SUCCESSFUL_DATA_UPDATE);
    }

    @Override
    @PatchMapping(value = LINKEDIN_MAPPING)
    public final DataResponse updateLinkedinData(@RequestParam String updateData,
                                                 @AuthenticationPrincipal User user) {
        log.info("Updating users linkedin data with: \n{}", updateData);

        userService.updateLinkedinData(updateData, user);

        return new DataResponse(SUCCESSFUL_DATA_UPDATE);
    }

    @Override
    @PatchMapping(value = GITHUB_MAPPING)
    public final DataResponse updateGithubData(@RequestParam String updateData,
                                               @AuthenticationPrincipal User user) {
        log.info("Updating users github data with: \n{}", updateData);

        userService.updateGithubData(updateData, user);

        return new DataResponse(SUCCESSFUL_DATA_UPDATE);
    }

    @Override
    @PatchMapping(value = LOCATION_MAPPING)
    public final DataResponse updateUsersLocation(@RequestParam String updateData,
                                                  @AuthenticationPrincipal User user) {
        log.info("Updating users location data with: \n{}", updateData);

        userService.updateUsersLocation(updateData, user);

        return new DataResponse(SUCCESSFUL_DATA_UPDATE);
    }

    @Override
    @PatchMapping(value = JOB_MAPPING)
    public final DataResponse updateUsersJob(@RequestParam String updateData,
                                             @AuthenticationPrincipal User user) {
        log.info("Updating users friendJob data with: \n{}", updateData);

        userService.updateUsersJob(updateData, user);

        return new DataResponse(SUCCESSFUL_DATA_UPDATE);
    }
}
