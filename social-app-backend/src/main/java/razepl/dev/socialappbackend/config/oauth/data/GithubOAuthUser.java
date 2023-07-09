package razepl.dev.socialappbackend.config.oauth.data;

import lombok.Builder;
import razepl.dev.socialappbackend.config.oauth.interfaces.IOAuthUser;

import java.time.LocalDate;
import java.util.Map;

@Builder
public record GithubOAuthUser(Map<String, Object> attributes) implements IOAuthUser {
    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getSurname() {
        return attributes.get("family_name").toString();
    }

    @Override
    public String getUsername() {
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }

    @Override
    public LocalDate getDateOfBirth() {
        Object date = attributes.get("birthdate");

        if (date == null) {
            throw new IllegalStateException("Date of birth cannot be null!");
        }
        return LocalDate.parse(date.toString());
    }
}
