package razepl.dev.socialappbackend.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import razepl.dev.socialappbackend.admin.interfaces.IAdminController;
import razepl.dev.socialappbackend.admin.interfaces.IAdminService;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.auth.apicalls.RegisterRequest;
import razepl.dev.socialappbackend.auth.interfaces.AuthInterface;
import razepl.dev.socialappbackend.home.data.UserData;

import java.util.List;

import static razepl.dev.socialappbackend.config.constants.Headers.ADMIN_MATCHERS;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = ADMIN_MATCHERS)
public class AdminController implements IAdminController {
    private static final String ENDPOINT_MATCHER = "/users";
    private final IAdminService adminService;
    private final AuthInterface authInterface;

    @Override
    @GetMapping(value = ENDPOINT_MATCHER)
    public final List<UserData> getUsersList() {
        return adminService.getUsersList();
    }

    @Override
    @DeleteMapping(value = ENDPOINT_MATCHER)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public final void deleteUser(@RequestParam String email) {
        adminService.deleteUser(email);
    }

    @Override
    @PatchMapping(value = ENDPOINT_MATCHER)
    public final void updateUsersName(@RequestParam String email, @RequestParam String newName) {
        adminService.updateUsersName(email, newName);
    }

    @Override
    @PostMapping(value = ENDPOINT_MATCHER)
    @ResponseStatus(value = HttpStatus.CREATED)
    public final AuthResponse createUser(@RequestBody RegisterRequest registerRequest) {
        return authInterface.registerUser(registerRequest);
    }
}
