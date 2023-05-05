package razepl.dev.socialappbackend.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import razepl.dev.socialappbackend.auth.apicalls.AuthResponse;
import razepl.dev.socialappbackend.auth.apicalls.RegisterRequest;
import razepl.dev.socialappbackend.auth.interfaces.AuthServiceInterface;
import razepl.dev.socialappbackend.friend.Friend;
import razepl.dev.socialappbackend.friend.FriendsRepository;
import razepl.dev.socialappbackend.user.User;
import razepl.dev.socialappbackend.user.interfaces.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/test")
public class TestDataController {
    private final AuthServiceInterface authInterface;
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    @PostMapping()
    public final ResponseEntity<AuthResponse> buildExampleDatabase() {
        log.info("Entering building example data!");

        AuthResponse response = registerUsers();

        addFriendsFor("jacek0@gmail.com");

        return ResponseEntity.ok(response);
    }

    private AuthResponse registerUsers() {
        String[] names = {"Jacek", "Ania", "Andrzej", "Asia", "Tomek", "Jan", "Mateusz", "Pawel", "Kacper", "Piotr"};
        String[] surnames = {"Kowalski", "Trzesniowski", "Evans", "Kobyla", "Wasilewski",
                "Sikorski", "Kaczmarczyk", "Tancerz", "Konieczny", "Maciejewicz"};
        String password = "Abc1!l1.DKk";
        LocalDate date = LocalDate.of(1999, 11, 11);
        AuthResponse response = null;

        for (int i = 0; i < names.length; i++) {
            String email = names[i].toLowerCase() + i + "@gmail.com";

            RegisterRequest request = RegisterRequest
                    .builder()
                    .name(names[i])
                    .surname(surnames[i])
                    .password(password)
                    .dateOfBirth(date)
                    .email(email)
                    .build();
            response = authInterface.register(request);
        }
        return response;
    }

    private void addFriendsFor(String username) {
        User userToGenFriends = userRepository.findByEmail(username).orElseThrow();

        List<User> userList = userRepository.findAll();

        for (User user : userList) {
            if (!user.getEmail().equals(username)) {
                Friend friend = Friend
                        .builder()
                        .friendName(user.getName())
                        .user(userToGenFriends)
                        .friendJob(user.getJob())
                        .build();
                friendsRepository.save(friend);
            }
        }
    }
}
