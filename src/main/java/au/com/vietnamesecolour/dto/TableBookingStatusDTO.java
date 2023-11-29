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
public class TableBookingStatusDTO {
    private Integer id;
    @NotBlank(message = "Booking status name must not be blank")
    private String bookingStatusName;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
}
