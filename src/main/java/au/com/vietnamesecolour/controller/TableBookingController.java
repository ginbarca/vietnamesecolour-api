package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.config.data.ViewMode;
import au.com.vietnamesecolour.dto.TableBookingDTO;
import au.com.vietnamesecolour.service.TableBookingService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@Validated
public class TableBookingController {

    private final TableBookingService bookingService;

    @JsonView({ViewMode.Private.class})
    @GetMapping("/api/v1/private/table-bookings")
    public ResponseEntity<ResponseData<ResponsePage<TableBookingDTO>>> findTableBooking(
            @RequestParam(name = "customerName", required = false) String customerName,
            @RequestParam(name = "mobileNumber", required = false) String mobileNumber,
            @RequestParam(name = "email", required = false) String email,
            @Valid @Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$", message = "Invalid date") @RequestParam(name = "bookingDateFrom", required = false) String bookingDateFrom,
            @Valid @Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$", message = "Invalid date") @RequestParam(name = "bookingDateTo", required = false) String bookingDateTo,
            @Valid @Min(value = 1, message = "Page must be start from 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Page size must be start from 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) throws ParseException {
        ResponseData<ResponsePage<TableBookingDTO>> responseData = bookingService.findTableBooking(customerName, mobileNumber, email, bookingDateFrom, bookingDateTo, page, pageSize);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @JsonView({ViewMode.Public.class})
    @PostMapping("/api/v1/public/table-bookings")
    public ResponseEntity<ResponseData<TableBookingDTO>> bookTable(
            @Valid @RequestBody TableBookingDTO bookingDTO
    ) throws ParseException {
        ResponseData<TableBookingDTO> responseData = bookingService.createTableBooking(bookingDTO);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @JsonView({ViewMode.Private.class})
    @PostMapping("/api/v1/private/table-bookings")
    public ResponseEntity<ResponseData<TableBookingDTO>> createTableBooking(
            @Valid @RequestBody TableBookingDTO bookingDTO
    ) throws ParseException {
        ResponseData<TableBookingDTO> responseData = bookingService.createTableBooking(bookingDTO);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PatchMapping("/api/v1/private/table-bookings/{id}")
    public ResponseEntity<ResponseData<TableBookingDTO>> updateTableBooking(
            @PathVariable(name = "id") Integer id,
            @Valid @RequestBody TableBookingDTO bookingDTO
    ) throws ParseException {
        ResponseData<TableBookingDTO> responseData = bookingService.updateTableBooking(id, bookingDTO);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @DeleteMapping("/api/v1/private/table-bookings/{id}")
    public ResponseEntity<ResponseData<Void>> deleteTableBookingById(@PathVariable(name = "id") Integer id) {
        ResponseData<Void> responseData = bookingService.deleteTableBookingById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @GetMapping("/api/v1/private/table-bookings/{id}")
    public ResponseEntity<ResponseData<TableBookingDTO>> getTableBookingById(@PathVariable(name = "id") Integer id) {
        ResponseData<TableBookingDTO> responseData = bookingService.getTableBookingById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }
}
