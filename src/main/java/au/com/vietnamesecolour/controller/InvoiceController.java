package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.config.data.ViewMode;
import au.com.vietnamesecolour.dto.InvoiceDTO;
import au.com.vietnamesecolour.service.InvoiceService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class InvoiceController {

    private final InvoiceService invoiceService;

    @JsonView({ViewMode.Public.class})
    @GetMapping("/api/v1/public/invoices")
    public ResponseEntity<ResponseData<ResponsePage<InvoiceDTO>>> findInvoice(
            @RequestParam(name = "invoiceCode", required = false, defaultValue = "") String invoiceCode,
            @Valid @Min(value = 1, message = "Page must be start from 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Page size must be start from 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        ResponseData<ResponsePage<InvoiceDTO>> responseData = invoiceService.findInvoice(invoiceCode, page, pageSize);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping("/api/v1/private/invoice")
    public ResponseEntity<ResponseData<InvoiceDTO>> createInvoice(@Valid @RequestBody InvoiceDTO payload) {
        ResponseData<InvoiceDTO> responseData = invoiceService.createInvoice(payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PatchMapping("/api/v1/private/invoice/{id}")
    public ResponseEntity<ResponseData<InvoiceDTO>> updateInvoice(
            @PathVariable(name = "id") Integer id,
            @Valid @RequestBody InvoiceDTO payload
    ) {
        ResponseData<InvoiceDTO> responseData = invoiceService.updateInvoice(id, payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @DeleteMapping("/api/v1/private/invoice/{id}")
    public ResponseEntity<ResponseData<Void>> deleteInvoiceById(@PathVariable(name = "id") Integer id) {
        ResponseData<Void> responseData = invoiceService.deleteInvoiceById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @GetMapping("/api/v1/private/invoice/{id}")
    public ResponseEntity<ResponseData<InvoiceDTO>> getInvoiceById(@PathVariable(name = "id") Integer id) {
        ResponseData<InvoiceDTO> responseData = invoiceService.getInvoiceById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }
}
