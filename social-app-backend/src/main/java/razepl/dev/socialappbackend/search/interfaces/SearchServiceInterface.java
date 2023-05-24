package razepl.dev.socialappbackend.search.interfaces;

import razepl.dev.socialappbackend.entities.user.User;
import razepl.dev.socialappbackend.search.data.UserSearchData;

import java.util.List;

public interface SearchServiceInterface {
    List<UserSearchData> getListOfUserBasedOnPattern(String pattern, int numOfSite, User user);
}
