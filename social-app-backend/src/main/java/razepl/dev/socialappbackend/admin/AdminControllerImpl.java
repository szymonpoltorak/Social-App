package razepl.dev.socialappbackend.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.admin.interfaces.AdminController;
import razepl.dev.socialappbackend.admin.interfaces.AdminService;
import razepl.dev.socialappbackend.auth.data.AuthResponse;
import razepl.dev.socialappbackend.auth.data.RegisterRequest;
import razepl.dev.socialappbackend.auth.interfaces.AuthController;
import razepl.dev.socialappbackend.home.data.UserResponse;

import java.util.List;

import static razepl.dev.socialappbackend.admin.Constants.ADMIN_MAPPING;
import static razepl.dev.socialappbackend.admin.Constants.ENDPOINT_MATCHER;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ADMIN_MAPPING)
public class AdminControllerImpl implements AdminController {
    private final AdminService adminService;
    private final AuthController authInterface;

    @Override
    @GetMapping(value = ENDPOINT_MATCHER)
    public final List<UserResponse> getUsersList() {
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
