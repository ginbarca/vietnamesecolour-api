package au.com.vietnamesecolour.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableBookingDTO {

    private Integer id;
    private String customerName;
    private String mobileNumber;
    private String email;
    private Integer numberOfPeople;
    private String bookingDate;
    private String bookingTime;
    private String note;
    @NotNull(message = "Booking status ID must not be null")
    private Integer bookingStatusId;
    private String bookingStatusName;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
}
