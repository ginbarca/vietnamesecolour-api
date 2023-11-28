package au.com.vietnamesecolour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnitDTO {

    private Integer id;
    private String unitName;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
}
