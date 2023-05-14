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
import razepl.dev.socialappbackend.post.Post;
import razepl.dev.socialappbackend.post.PostRepository;
import razepl.dev.socialappbackend.user.User;
import razepl.dev.socialappbackend.user.interfaces.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/test")
public class TestDataController {
    private final AuthServiceInterface authInterface;
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @PostMapping
    public final ResponseEntity<AuthResponse> buildExampleDatabase() {
        log.info("Entering building example data!");

        AuthResponse response = registerUsers();

        addFriendsFor("jacek0@gmail.com");

        addPosts();

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
                        .friendName(user.getFullName())
                        .friendUsername(user.getUsername())
                        .user(userToGenFriends)
                        .friendJob(user.getJob())
                        .build();
                friendsRepository.save(friend);
            }
        }
    }

    private void addPosts() {
        LocalDate date = LocalDate.of(2023, 11, 11);
        String[] contents = {"Hello world!", "What is going on guys?", "How to pass my exams?",
                "Lets play football!", "Its time for sleep.", "Yesterday I started a new work!",
                "Live is funny!", "Who wants to play basketball??", "Life is funny!"
        };
        User jacek = userRepository.findByEmail("jacek0@gmail.com").orElseThrow();
        User ania = userRepository.findByEmail("ania1@gmail.com").orElseThrow();
        User andrzej = userRepository.findByEmail("andrzej2@gmail.com").orElseThrow();
        String location = "Warsaw, Poland";

        for (int i = 0; i < contents.length; i++) {
            if (i % 3 == 0) {
                Post post = Post
                        .builder()
                        .postLocation(location)
                        .postContent(contents[i])
                        .postDate(date)
                        .user(jacek)
                        .numOfLikes(25L)
                        .build();
                postRepository.save(post);
            } else if (i % 3 == 1) {
                Post post = Post
                        .builder()
                        .postLocation(location)
                        .postContent(contents[i])
                        .postDate(date)
                        .user(ania)
                        .numOfLikes(10L)
                        .build();
                postRepository.save(post);
            } else {
                Post post = Post
                        .builder()
                        .postLocation(location)
                        .postContent(contents[i])
                        .postDate(date)
                        .user(andrzej)
                        .numOfLikes(34L)
                        .build();
                postRepository.save(post);
            }
        }
    }
}
