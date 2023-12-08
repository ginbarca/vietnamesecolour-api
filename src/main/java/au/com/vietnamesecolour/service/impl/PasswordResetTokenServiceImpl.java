package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.entity.PasswordResetToken;
import au.com.vietnamesecolour.entity.User;
import au.com.vietnamesecolour.repos.PasswordResetTokenRepository;
import au.com.vietnamesecolour.repos.UserRepository;
import au.com.vietnamesecolour.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final PasswordResetTokenRepository pwdResetTokenRepo;
    private final UserRepository userRepository;

    @Override
    public boolean generateResetTokenForUser(String email, String passwordToken) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            PasswordResetToken passwordRestToken = new PasswordResetToken(passwordToken, user.get());
            pwdResetTokenRepo.save(passwordRestToken);
            return true;
        }
        return false;
    }

    @Override
    public String validatePasswordResetToken(String passwordResetToken) {
        PasswordResetToken passwordToken = pwdResetTokenRepo.findByToken(passwordResetToken);
        if (passwordToken == null) {
            return "invalid";
        }
        Calendar calendar = Calendar.getInstance();
        if ((passwordToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            return "expired";
        }
        return "valid";
    }

    @Override
    public Optional<User> findUserByPasswordToken(String passwordResetToken) {
        return Optional.ofNullable(pwdResetTokenRepo.findByToken(passwordResetToken).getUser());
    }

    @Override
    public PasswordResetToken findPasswordResetToken(String token) {
        return pwdResetTokenRepo.findByToken(token);
    }
}
