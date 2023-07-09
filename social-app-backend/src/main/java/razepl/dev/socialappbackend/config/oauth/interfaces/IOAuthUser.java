package razepl.dev.socialappbackend.config.oauth.interfaces;

import java.time.LocalDate;
import java.util.Map;

public interface IOAuthUser {
    String getId();

    String getSurname();

    String getUsername();

    String getName();

    LocalDate getDateOfBirth();

    Map<String, Object> attributes();
}
