package au.com.vietnamesecolour.dto;

import au.com.vietnamesecolour.config.data.ViewMode;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishInfoDTO {

    @JsonView({ViewMode.Private.class})
    private Integer id;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String dishName;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private Float price;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String dishDescription;

    @JsonView({ViewMode.Private.class})
    private String dishImagePath;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String dishImageName;

    @JsonView({ViewMode.Private.class})
    private String dishGroupName;

    @JsonView({ViewMode.Private.class})
    @NotNull(message = "Group ID must not be null")
    private Integer dishGroupId;
    private String unitName;

    @JsonView({ViewMode.Private.class})
    @NotNull(message = "Unit ID must not be null")
    private Integer unitId;

    @JsonView({ViewMode.Private.class})
    private String createdDate;

    @JsonView({ViewMode.Private.class})
    private String updatedDate;

    @JsonView({ViewMode.Private.class})
    private String createdBy;

    @JsonView({ViewMode.Private.class})
    private String updatedBy;
}
