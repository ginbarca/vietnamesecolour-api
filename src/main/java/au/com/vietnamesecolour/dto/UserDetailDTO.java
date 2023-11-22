package au.com.vietnamesecolour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String mobile;
    private String gender;
    private String roleName;
}
