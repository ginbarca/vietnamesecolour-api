package au.com.vietnamesecolour.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDTO {

    private Integer id;
    @NotBlank(message = "Order status must not be blank")
    private String orderStatusName;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;

}
