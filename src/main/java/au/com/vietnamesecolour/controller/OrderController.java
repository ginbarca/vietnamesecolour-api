package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.config.data.ViewMode;
import au.com.vietnamesecolour.dto.OrderDetailDTO;
import au.com.vietnamesecolour.service.OrderService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService comboService;

    @GetMapping("/private/orders")
    public ResponseEntity<ResponseData<ResponsePage<OrderDetailDTO>>> findOrder(
            @RequestParam(name = "custName", required = false, defaultValue = "") String custName,
            @RequestParam(name = "custEmail", required = false, defaultValue = "") String custEmail,
            @RequestParam(name = "mobilePhone", required = false, defaultValue = "") String mobilePhone,
            @Valid @Min(value = 1, message = "Page must be start from 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Page size must be start from 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        ResponseData<ResponsePage<OrderDetailDTO>> responseData = comboService.findOrder(custName, custEmail, mobilePhone, page, pageSize);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @JsonView({ViewMode.Private.class})
    @PostMapping("/private/orders")
    public ResponseEntity<ResponseData<OrderDetailDTO>> createOrder(@Valid @RequestBody OrderDetailDTO payload) throws ParseException {
        ResponseData<OrderDetailDTO> responseData = comboService.createOrder(payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @JsonView({ViewMode.Public.class})
    @PostMapping("/public/orders")
    public ResponseEntity<ResponseData<OrderDetailDTO>> makeOrder(@Valid @RequestBody OrderDetailDTO payload) throws ParseException {
        ResponseData<OrderDetailDTO> responseData = comboService.createOrder(payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PatchMapping("/private/orders/{id}")
    public ResponseEntity<ResponseData<OrderDetailDTO>> updateOrder(
            @PathVariable(name = "id") Integer id,
            @Valid @RequestBody OrderDetailDTO payload
    ) throws ParseException {
        ResponseData<OrderDetailDTO> responseData = comboService.updateOrder(id, payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @DeleteMapping("/private/orders/{id}")
    public ResponseEntity<ResponseData<Void>> deleteOrderById(@PathVariable(name = "id") Integer id) {
        ResponseData<Void> responseData = comboService.deleteOrderById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @GetMapping("/private/orders/{id}")
    public ResponseEntity<ResponseData<OrderDetailDTO>> getOrderById(@PathVariable(name = "id") Integer id) {
        ResponseData<OrderDetailDTO> responseData = comboService.getOrderById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }
}
