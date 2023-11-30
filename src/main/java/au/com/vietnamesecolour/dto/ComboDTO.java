package au.com.vietnamesecolour.dto;

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
public class ComboDTO {
    private Integer id;
    @NotBlank(message = "Group name must not be blank")
    private String comboName;
    private Float comboPrice;
    private List<DishInfoDTO> dishList;
    private Integer[] dishIds;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
}
