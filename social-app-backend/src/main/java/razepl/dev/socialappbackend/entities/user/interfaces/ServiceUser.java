package razepl.dev.socialappbackend.entities.user.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * A service user is a person who uses a service provided by an application.
 * A service user has an ID, an age, an email address, a first name, a last name and a password.
 * This interface defines the methods to access and modify the properties of a service user.
 */
public interface ServiceUser extends UserDetails, UserPropertyInterface {
    /**
     * Returns the ID of this user.
     *
     * @return the ID of this user
     */
    long getUserId();

    /**
     * Returns the age of this user in years.
     *
     * @return the age of this user in years
     */
    int getAge();

    String getFullName();
}
