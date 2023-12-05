package au.com.vietnamesecolour.dto;

import au.com.vietnamesecolour.config.data.ViewMode;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishGroupDTO {

    @JsonView({ViewMode.Private.class})
    private Integer id;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @NotBlank(message = "Group name must not be blank")
    private String dishGroupName;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private List<DishInfoDTO> dishList;

    @JsonView({ViewMode.Private.class})
    private String createdDate;

    @JsonView({ViewMode.Private.class})
    private String updatedDate;

    @JsonView({ViewMode.Private.class})
    private String createdBy;

    @JsonView({ViewMode.Private.class})
    private String updatedBy;

}
