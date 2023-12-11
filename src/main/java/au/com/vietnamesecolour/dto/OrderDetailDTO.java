package au.com.vietnamesecolour.dto;

import au.com.vietnamesecolour.config.data.ViewMode;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    @JsonView({ViewMode.Private.class})
    private Integer id;

    @JsonView({ViewMode.Private.class})
    private Integer custId;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @NotBlank(message = "Customer name must not be blank")
    private String custName;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @NotBlank(message = "Customer email must not be blank")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email")
    private String custEmail;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @NotBlank(message = "Customer mobile number must not be blank")
    private String custMobile;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @NotBlank(message = "Customer address must not be blank")
    private String custAddress;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @Size(max = 500, message = "Note max length is 500 characters")
    private String note;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private Float discount;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @NotNull(message = "Total price must not be null")
    private Float totalAmount;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$", message = "Invalid date")
    private String orderDate;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid time")
    private String orderTime;

    @JsonView({ViewMode.Private.class})
    @NotNull(message = "Order status ID must not be null")
    private Integer orderStatusId;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String orderStatusName;

    @JsonView({ViewMode.Private.class})
    @NotNull(message = "Order type ID must not be null")
    private Integer orderTypeId;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String orderTypeName;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private List<DishInfoDTO> dishList;

    @JsonView({ViewMode.Private.class})
    @NotNull(message = "Order must have at least one item")
    private Integer[] dishIds;

    @JsonView({ViewMode.Private.class})
    private String createdDate;

    @JsonView({ViewMode.Private.class})
    private String updatedDate;

    @JsonView({ViewMode.Private.class})
    private String createdBy;

    @JsonView({ViewMode.Private.class})
    private String updatedBy;
}
