package razepl.dev.socialappbackend.entities.user.interfaces;

public interface UserPropertyInterface {
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
