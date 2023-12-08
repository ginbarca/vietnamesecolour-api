package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.entity.PasswordResetToken;
import au.com.vietnamesecolour.entity.User;

import java.util.Optional;

public interface PasswordResetTokenService {

    boolean generateResetTokenForUser(String email, String passwordToken);

    String validatePasswordResetToken(String passwordResetToken);

    Optional<User> findUserByPasswordToken(String passwordResetToken);

    PasswordResetToken findPasswordResetToken(String token);

}
