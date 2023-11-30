package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.dto.PaymentStatusDTO;
import au.com.vietnamesecolour.service.PaymentStatusService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/private/setting/payment-status")
@RequiredArgsConstructor
@Validated
public class PaymentStatusController {

    private final PaymentStatusService paymentStatusService;

    @GetMapping
    public ResponseEntity<ResponseData<ResponsePage<PaymentStatusDTO>>> findPaymentStatus(
            @RequestParam(name = "paymentStatusName", required = false, defaultValue = "") String paymentStatusName,
            @Valid @Min(value = 1, message = "Page must be start from 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Page size must be start from 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        ResponseData<ResponsePage<PaymentStatusDTO>> responseData = paymentStatusService.findPaymentStatus(paymentStatusName, page, pageSize);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping
    public ResponseEntity<ResponseData<PaymentStatusDTO>> createPaymentStatus(@Valid @RequestBody PaymentStatusDTO payload) {
        ResponseData<PaymentStatusDTO> responseData = paymentStatusService.createPaymentStatus(payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData<PaymentStatusDTO>> updatePaymentStatus(
            @PathVariable(name = "id") Integer id,
            @Valid @RequestBody PaymentStatusDTO payload
    ) {
        ResponseData<PaymentStatusDTO> responseData = paymentStatusService.updatePaymentStatus(id, payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deletePaymentStatusById(@PathVariable(name = "id") Integer id) {
        ResponseData<Void> responseData = paymentStatusService.deletePaymentStatusById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<PaymentStatusDTO>> getPaymentStatusById(@PathVariable(name = "id") Integer id) {
        ResponseData<PaymentStatusDTO> responseData = paymentStatusService.getPaymentStatusById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }
}
