package au.com.vietnamesecolour.dto;

import au.com.vietnamesecolour.config.data.ViewMode;
import com.fasterxml.jackson.annotation.JsonView;
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

    @JsonView({ViewMode.Private.class})
    private Integer id;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @NotBlank(message = "Customer name must not be blank")
    private String customerName;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @NotBlank(message = "Mobile number must not be blank")
    private String mobileNumber;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email")
    private String email;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @NotNull(message = "Number of people must not be null")
    private Integer numberOfPeople;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$", message = "Invalid date")
    private String bookingDate;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid time")
    private String bookingTime;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    @Size(max = 500, message = "Note max length is 500 characters")
    private String note;

    @JsonView({ViewMode.Private.class})
    @NotNull(message = "Booking status ID must not be null")
    private Integer bookingStatusId;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String bookingStatusName;

    @JsonView({ViewMode.Private.class})
    private String createdDate;

    @JsonView({ViewMode.Private.class})
    private String updatedDate;

    @JsonView({ViewMode.Private.class})
    private String createdBy;

    @JsonView({ViewMode.Private.class})
    private String updatedBy;
}
