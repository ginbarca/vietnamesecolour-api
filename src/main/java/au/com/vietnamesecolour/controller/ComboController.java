package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.dto.ComboDTO;
import au.com.vietnamesecolour.service.ComboService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/private")
@RequiredArgsConstructor
@Validated
public class ComboController {

    private final ComboService comboService;

    @GetMapping("/combos")
    public ResponseEntity<ResponseData<ResponsePage<ComboDTO>>> findCombo(
            @RequestParam(name = "comboName", required = false, defaultValue = "") String comboName,
            @Valid @Min(value = 1, message = "Page must be start from 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Page size must be start from 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        ResponseData<ResponsePage<ComboDTO>> responseData = comboService.findCombo(comboName, page, pageSize);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping("/combo")
    public ResponseEntity<ResponseData<ComboDTO>> createCombo(@Valid @RequestBody ComboDTO payload) {
        ResponseData<ComboDTO> responseData = comboService.createCombo(payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PatchMapping("/combo/{id}")
    public ResponseEntity<ResponseData<ComboDTO>> updateCombo(
            @PathVariable(name = "id") Integer id,
            @Valid @RequestBody ComboDTO payload
    ) {
        ResponseData<ComboDTO> responseData = comboService.updateCombo(id, payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @DeleteMapping("/combo/{id}")
    public ResponseEntity<ResponseData<Void>> deleteComboById(@PathVariable(name = "id") Integer id) {
        ResponseData<Void> responseData = comboService.deleteComboById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @GetMapping("/combo/{id}")
    public ResponseEntity<ResponseData<ComboDTO>> getComboById(@PathVariable(name = "id") Integer id) {
        ResponseData<ComboDTO> responseData = comboService.getComboById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }
}
