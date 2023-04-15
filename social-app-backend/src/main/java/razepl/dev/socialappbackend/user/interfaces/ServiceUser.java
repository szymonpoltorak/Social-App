package razepl.dev.socialappbackend.user.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * A service user is a person who uses a service provided by an application.
 * A service user has an ID, an age, an email address, a first name, a last name and a password.
 * This interface defines the methods to access and modify the properties of a service user.
 */
public interface ServiceUser extends UserDetails {
    /**
     * Returns the ID of this user.
     *
     * @return the ID of this user
     */
    long getId();

    /**
     * Returns the age of this user in years.
     *
     * @return the age of this user in years
     */
    int getAge();

    /**
     * Returns the email address of this user.
     *
     * @return the email address of this user
     */
    String getEmail();

    /**
     * Returns the first name of this user.
     *
     * @return the first name of this user
     */
    String getName();

    /**
     * Returns the last name of this user.
     *
     * @return the last name of this user
     */
    String getSurname();

    /**
     * Returns password of this user.
     *
     * @return the password of this user
     */
    String getPassword();
}
