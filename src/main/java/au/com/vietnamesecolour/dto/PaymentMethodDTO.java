package au.com.vietnamesecolour.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDTO {
    private Integer id;
    @NotBlank(message = "Payment method name must not be blank")
    private String paymentMethodName;
    @NotNull(message = "Payment method must be enabled or disabled")
    private Boolean enabled;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
}
