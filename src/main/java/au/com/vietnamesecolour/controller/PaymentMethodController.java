package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.dto.PaymentMethodDTO;
import au.com.vietnamesecolour.service.PaymentMethodService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/private/setting/payment-method")
@RequiredArgsConstructor
@Validated
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<ResponseData<ResponsePage<PaymentMethodDTO>>> findPaymentMethod(
            @RequestParam(name = "paymentMethodName", required = false, defaultValue = "") String paymentMethodName,
            @RequestParam(name = "enabled", required = false, defaultValue = "") Boolean isEnabled,
            @Valid @Min(value = 1, message = "Page must be start from 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Page size must be start from 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        ResponseData<ResponsePage<PaymentMethodDTO>> responseData = paymentMethodService.findPaymentMethod(paymentMethodName, isEnabled, page, pageSize);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping
    public ResponseEntity<ResponseData<PaymentMethodDTO>> createPaymentMethod(@Valid @RequestBody PaymentMethodDTO payload) {
        ResponseData<PaymentMethodDTO> responseData = paymentMethodService.createPaymentMethod(payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData<PaymentMethodDTO>> updatePaymentMethod(
            @PathVariable(name = "id") Integer id,
            @Valid @RequestBody PaymentMethodDTO payload
    ) {
        ResponseData<PaymentMethodDTO> responseData = paymentMethodService.updatePaymentMethod(id, payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deletePaymentMethodById(@PathVariable(name = "id") Integer id) {
        ResponseData<Void> responseData = paymentMethodService.deletePaymentMethodById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<PaymentMethodDTO>> getPaymentMethodById(@PathVariable(name = "id") Integer id) {
        ResponseData<PaymentMethodDTO> responseData = paymentMethodService.getPaymentMethodById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }
}
