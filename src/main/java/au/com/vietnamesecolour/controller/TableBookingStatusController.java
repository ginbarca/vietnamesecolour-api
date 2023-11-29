package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.dto.TableBookingStatusDTO;
import au.com.vietnamesecolour.service.TableBookingStatusService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/private/table-booking-status")
@RequiredArgsConstructor
@Validated
public class TableBookingStatusController {

    private final TableBookingStatusService bookingStatusService;

    @GetMapping
    public ResponseEntity<ResponseData<ResponsePage<TableBookingStatusDTO>>> findTableBookingStatus(
            @RequestParam(name = "bookingStatusName", required = false, defaultValue = "") String bookingStatusName,
            @Valid @Min(value = 1, message = "Page must be start from 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Page size must be start from 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        ResponseData<ResponsePage<TableBookingStatusDTO>> responseData = bookingStatusService.findTableBookingStatus(bookingStatusName, page, pageSize);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping
    public ResponseEntity<ResponseData<TableBookingStatusDTO>> createTableBookingStatus(@Valid @RequestBody TableBookingStatusDTO payload) {
        ResponseData<TableBookingStatusDTO> responseData = bookingStatusService.createTableBookingStatus(payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData<TableBookingStatusDTO>> updateTableBookingStatus(
            @PathVariable(name = "id") Integer id,
            @Valid @RequestBody TableBookingStatusDTO payload
    ) {
        ResponseData<TableBookingStatusDTO> responseData = bookingStatusService.updateTableBookingStatus(id, payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteTableBookingStatusById(@PathVariable(name = "id") Integer id) {
        ResponseData<Void> responseData = bookingStatusService.deleteTableBookingStatusById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<TableBookingStatusDTO>> getTableBookingStatusById(@PathVariable(name = "id") Integer id) {
        ResponseData<TableBookingStatusDTO> responseData = bookingStatusService.getTableBookingStatusById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }
}
