package razepl.dev.socialappbackend.search;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import razepl.dev.socialappbackend.config.enums.Role;
import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.entities.user.interfaces.UserRepository;
import razepl.dev.socialappbackend.exceptions.NegativeIdException;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;
import razepl.dev.socialappbackend.search.data.UserSearchData;
import razepl.dev.socialappbackend.search.interfaces.SearchServiceInterface;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SearchServiceTest {
    @Autowired
    private SearchServiceInterface searchService;

    @Autowired
    private UserRepository userRepository;

    private User jacek;

    private User ania;

    @BeforeEach
    final void init() {
        jacek = User
                .builder()
                .name("Jacek")
                .surname("Wasilewski")
                .email("jacek0@gmail.com")
                .password("AbcdDef123#!")
                .dateOfBirth(LocalDate.now())
                .role(Role.USER)
                .build();
        ania = User
                .builder()
                .name("Ania")
                .surname("Trzesniowska")
                .email("ania1@gmail.com")
                .password("AbcdDef123#!")
                .dateOfBirth(LocalDate.now())
                .role(Role.USER)
                .build();
        userRepository.save(ania);
        userRepository.save(jacek);
    }

    @Test
    final void test_getListOfUserBasedOnPattern_null() {
        // given

        // when

        // then
        assertThrows(NullArgumentException.class, () -> searchService.getListOfUserBasedOnPattern(null, 0, jacek));
    }

    @Test
    final void test_getListOfUserBasedOnPattern_negative_num_of_site() {
        // given

        // when

        // then
        assertThrows(NegativeIdException.class, () -> searchService.getListOfUserBasedOnPattern("", -1, jacek));
    }

    @Test
    final void test_getListOfUserBasedOnPattern_pattern_match_current_user() {
        // given
        int numOfSite = 0;
        String pattern = "Jacek";

        // when
        List<UserSearchData> result = searchService.getListOfUserBasedOnPattern(pattern, numOfSite, jacek);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    final void test_getListOfUserBasedOnPattern_pattern_match_one_user() {
        // given
        int numOfSite = 0;
        String pattern = "Ania";

        // when
        List<UserSearchData> result = searchService.getListOfUserBasedOnPattern(pattern, numOfSite, jacek);

        // then
        assertEquals(1, result.size());
    }
}
