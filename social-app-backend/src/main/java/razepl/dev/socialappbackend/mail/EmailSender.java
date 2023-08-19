package razepl.dev.socialappbackend.mail;

/**
 * This interface is responsible for sending emails to users.
 */
public interface EmailSender {
    /**
     * This method sends an email to the user.
     *
     * @param to      The email address of the user.
     * @param subject The subject of the email.
     * @param content The content of the email.
     */
    void sendEmail(String to, String subject, String content);
}
