package au.com.vietnamesecolour.dto;

import lombok.Data;

@Data
public class ResetPasswordRequestDTO {
    private String email;
    private String oldPassword;
    private String newPassword;
}
