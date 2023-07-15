package razepl.dev.socialappbackend.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.admin.interfaces.IAdminService;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.home.data.UserData;
import razepl.dev.socialappbackend.home.interfaces.DataServiceInterface;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService implements IAdminService {
    private final UserRepository userRepository;
    private final DataServiceInterface dataService;

    @Override
    public final List<UserData> getUsersList() {
        log.info("Getting users list");

        return userRepository
                .findAll()
                .stream()
                .map(dataService::buildUserData)
                .toList();
    }

    @Override
    public final void deleteUser(String email) {
        log.info("Deleting user with email: {}", email);

        userRepository.deleteByEmail(email);
    }

    @Override
    public final void updateUsersName(String email, String newName) {
        log.info("Updating user with email: {}", email);
        log.info("New name: {}", newName);

        userRepository
                .findByEmail(email)
                .ifPresent(user -> {
                    user.setName(newName);

                    userRepository.save(user);
                });
    }
}
