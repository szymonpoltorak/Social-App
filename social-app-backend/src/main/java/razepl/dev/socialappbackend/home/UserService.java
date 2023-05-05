package razepl.dev.socialappbackend.home;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import razepl.dev.socialappbackend.home.data.UserDataRequest;
import razepl.dev.socialappbackend.home.interfaces.UserServiceInterface;
import razepl.dev.socialappbackend.user.User;
import razepl.dev.socialappbackend.user.interfaces.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    @Override
    public void updateTwitterData(UserDataRequest request) {
        User user = userRepository.findByEmail(request.username()).orElseThrow();

        user.setTwitter(request.updateData());

        userRepository.save(user);
    }

    @Override
    public void updateLinkedinData(UserDataRequest request) {
        User user = userRepository.findByEmail(request.username()).orElseThrow();

        user.setLinkedin(request.updateData());

        userRepository.save(user);
    }

    @Override
    public void updateGithubData(UserDataRequest request) {
        User user = userRepository.findByEmail(request.username()).orElseThrow();

        user.setGithub(request.updateData());

        userRepository.save(user);
    }

    @Override
    public void updateUsersLocation(UserDataRequest request) {
        User user = userRepository.findByEmail(request.username()).orElseThrow();

        user.setLocation(request.updateData());

        userRepository.save(user);
    }

    @Override
    public void updateUsersJob(UserDataRequest request) {
        User user = userRepository.findByEmail(request.username()).orElseThrow();

        user.setJob(request.updateData());

        userRepository.save(user);
    }
}
