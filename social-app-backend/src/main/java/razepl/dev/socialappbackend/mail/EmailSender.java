package razepl.dev.socialappbackend.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * This class is responsible for sending emails to users.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSender implements IEmailSender {
    private final JavaMailSender mailSender;

    @Override
    public final void sendEmail(String to, String subject, String content) {
        log.info("Sending email to: {}", to);
        log.info("Subject: {}", subject);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());

            helper.setText(content, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("noreply@socialapp.com");

            mailSender.send(mimeMessage);

        } catch (MessagingException exception) {
            log.error("Error while sending email to: {}", to);
            log.error("Error message : {}", exception.getMessage());

            throw new IllegalStateException("Error while sending email");
        }
    }
}
