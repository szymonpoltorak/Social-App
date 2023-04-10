package razepl.dev.socialappbackend.user;

public interface ServiceUser {
    long getId();

    int getAge();

    String getEmail();

    String getName();

    String getSurname();

    int getPasswordHashCode();
}
