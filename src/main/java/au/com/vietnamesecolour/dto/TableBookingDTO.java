package au.com.vietnamesecolour.dto;

import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Customer name must not be blank")
    private String customerName;
    @NotBlank(message = "Mobile number must not be blank")
    private String mobileNumber;
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email")
    private String email;
    @NotNull(message = "Number of people must not be null")
    private Integer numberOfPeople;
    @Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$", message = "Invalid date")
    private String bookingDate;
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid time")
    private String bookingTime;
    @Size(max = 500, message = "Note max length is 500 characters")
    private String note;
    @NotNull(message = "Booking status ID must not be null")
    private Integer bookingStatusId;
    private String bookingStatusName;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
}
