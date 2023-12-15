package au.com.vietnamesecolour.dto;

import au.com.vietnamesecolour.config.data.ViewMode;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    @JsonView({ViewMode.Private.class})
    private Integer id;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String invoiceCode;

    @JsonView({ViewMode.Private.class})
    @NotNull(message = "Order ID must not be null")
    private Integer orderId;

    @JsonView({ViewMode.Private.class})
    @NotNull(message = "Payment status ID must not be null")
    private Integer pmStatusId;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String pmStatusName;

    @JsonView({ViewMode.Private.class})
    @NotNull(message = "Payment method ID must not be null")
    private Integer pmMethodId;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String pmMethodName;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private List<OrderDishDTO> orderDishes;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @Size(max = 500, message = "Note max length is 500 characters")
    private String note;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private Float discount;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private Float subtotal;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private Float total;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String createdDate;

    @JsonView({ViewMode.Private.class})
    private String updatedDate;

    @JsonView({ViewMode.Private.class})
    private String createdBy;

    @JsonView({ViewMode.Private.class})
    private String updatedBy;
}
