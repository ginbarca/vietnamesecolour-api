package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.dto.ChangePasswordRequestDTO;
import au.com.vietnamesecolour.entity.User;
import au.com.vietnamesecolour.repos.UserRepository;
import au.com.vietnamesecolour.utils.CommonUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final PasswordResetTokenService pwdResetTokenService;
    private final EmailService emailService;

    public void changePassword(ChangePasswordRequestDTO request, Principal connectedUser) {

        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }

    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public ResponseData<Void> sendResetPwdLink(String applicationUrl, String email) throws MessagingException, UnsupportedEncodingException {
        ResponseData<Void> responseData;
        String passwordResetToken = CommonUtils.randomString(30);
        String verifyUrl = applicationUrl + "/api/v1/auth/reset-password?token=" + passwordResetToken;
        boolean isResetTokenGenerated = pwdResetTokenService.generateResetTokenForUser(email, passwordResetToken);
        if (isResetTokenGenerated) {
            emailService.sendPasswordResetVerificationEmail(verifyUrl, email);
            responseData = new ResponseData<>();
        } else {
            responseData = new ResponseData<>(CommonErrorCode.GENERATE_PWD_RESET_TOKEN_ERROR.getCode(), CommonErrorCode.GENERATE_PWD_RESET_TOKEN_ERROR.getMessage());
        }
        return responseData;
    }
}
