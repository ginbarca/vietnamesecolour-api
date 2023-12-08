package au.com.vietnamesecolour.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendPasswordResetVerificationEmail(String verifyUrl, String receiverEmail) throws MessagingException, UnsupportedEncodingException {
        String subject = "Password Reset Request Verification";
        String senderName = "Reset Password | VietnameseColour";
        String mailContent = getResetPwdHTMLContent().replace("{{verifyUrl}}", verifyUrl);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        FileSystemResource logo = new FileSystemResource(EmailService.class.getClassLoader().getResource("reset-pwd-template-email/images/artboard-26a2x.png").getFile());
        FileSystemResource resetIcon = new FileSystemResource(EmailService.class.getClassLoader().getResource("reset-pwd-template-email/images/image-1.png").getFile());

        helper.setFrom("no-reply@vietnamesecolour.com.au", senderName);
        helper.setTo(receiverEmail);
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        helper.addInline("logo", logo);
        helper.addInline("resetIcon", resetIcon);
        mailSender.send(message);
    }

    private String getResetPwdHTMLContent() {
        try {
            ClassLoader classLoader = EmailService.class.getClassLoader();
            URL resource = classLoader.getResource("reset-pwd-template-email/index.html");

            if (resource == null) {
                throw new IllegalArgumentException("file is not found!");
            } else {
                return IOUtils.toString(new FileInputStream(resource.getFile()), "UTF-8");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File(EmailService.class.getClassLoader().getResource("reset-pwd-template-email/images/artboard-26a2x.png").getFile());
        System.out.println(file.getName());
    }
}
