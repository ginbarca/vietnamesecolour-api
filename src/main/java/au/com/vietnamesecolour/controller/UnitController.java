package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.dto.UnitDTO;
import au.com.vietnamesecolour.service.UnitService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/private/units")
@RequiredArgsConstructor
@Validated
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    public ResponseEntity<ResponseData<ResponsePage<UnitDTO>>> findUnit(
            @RequestParam(name = "unitName", required = false, defaultValue = "") String unitName,
            @Valid @Min(value = 1, message = "Số trang phải bắt đầu từ 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Số bản ghi trên 1 trang phải bắt đầu từ 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        ResponseData<ResponsePage<UnitDTO>> responseData = unitService.findUnit(unitName, page, pageSize);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping
    public ResponseEntity<ResponseData<UnitDTO>> createUnit(@RequestBody UnitDTO payload) {
        ResponseData<UnitDTO> responseData = unitService.createUnit(payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData<UnitDTO>> updateUnit(
            @Valid @NotNull @PathVariable(name = "id") Integer id,
            @RequestBody UnitDTO payload
    ) {
        ResponseData<UnitDTO> responseData = unitService.updateUnit(id, payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteUnitById(@Valid @NotNull @PathVariable(name = "id") Integer id) {
        ResponseData<Void> responseData = unitService.deleteUnitById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<UnitDTO>> getUnitById(@Valid @NotNull @PathVariable(name = "id") Integer id) {
        ResponseData<UnitDTO> responseData = unitService.getUnitById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }
}
