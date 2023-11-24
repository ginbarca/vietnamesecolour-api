package au.com.vietnamesecolour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishGroupDTO {
    private Integer id;
    private String dishGroupName;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
}
