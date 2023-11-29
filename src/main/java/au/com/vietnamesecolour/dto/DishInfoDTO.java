package au.com.vietnamesecolour.dto;

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
    private Integer id;
    private String dishName;
    private Float price;
    private String dishDescription;
    private String dishImagePath;
    private String dishImageName;
    private String dishGroupName;
    @NotNull(message = "Group ID must not be null")
    private Integer dishGroupId;
    private String unitName;
    @NotNull(message = "Unit ID must not be null")
    private Integer unitId;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
}
