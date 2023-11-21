package au.com.vietnamesecolour.dto;

import au.com.vietnamesecolour.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String mobile;
    private String gender;
    private Role role;
}
