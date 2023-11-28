package au.com.vietnamesecolour.dto;

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
    private String dishGroupName;
//    @JsonIgnore
    private Integer dishGroupId;
    private String unitName;
//    @JsonIgnore
    private Integer unitId;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
}
