package razepl.dev.socialappbackend.search.interfaces;

import razepl.dev.socialappbackend.home.data.FriendData;

import java.util.List;

public interface SearchServiceInterface {
    List<FriendData> getListOfUserBasedOnPattern(String pattern, int numOfSite);
}
