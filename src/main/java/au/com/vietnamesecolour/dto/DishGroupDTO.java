package au.com.vietnamesecolour.dto;

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
    private Integer id;
    private String dishGroupName;
    private List<DishInfoDTO> dishList;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
}
