package au.com.vietnamesecolour.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendPasswordResetVerificationEmail(String verifyUrl, String receiverEmail) throws MessagingException, UnsupportedEncodingException {
        String subject = "Password Reset Request Verification";
        String senderName = "Reset Password | VietnameseColour";
        String mailContent = "<p> Hi,<br/> , </p>" +
                "<p><b>You recently requested to reset your password,</b>" + "" +
                "Please, follow the link below to complete the action.</p>" +
                "<a href=\"" + verifyUrl + "\">Reset password</a>" +
                "<p> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("no-reply@vietnamesecolour.com.au", senderName);
        messageHelper.setTo(receiverEmail);
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}
