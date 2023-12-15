package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.dto.UnitDTO;
import au.com.vietnamesecolour.service.UnitService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/private/setting")
@RequiredArgsConstructor
@Validated
public class UnitController {

    private final UnitService unitService;

    @GetMapping("/units")
    public ResponseEntity<ResponseData<ResponsePage<UnitDTO>>> findUnit(
            @RequestParam(name = "unitName", required = false, defaultValue = "") String unitName,
            @Valid @Min(value = 1, message = "Page must be start from 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Page size must be start from 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        ResponseData<ResponsePage<UnitDTO>> responseData = unitService.findUnit(unitName, page, pageSize);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping("/unit")
    public ResponseEntity<ResponseData<UnitDTO>> createUnit(@RequestBody UnitDTO payload) {
        ResponseData<UnitDTO> responseData = unitService.createUnit(payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PatchMapping("/unit/{id}")
    public ResponseEntity<ResponseData<UnitDTO>> updateUnit(
            @PathVariable(name = "id") Integer id,
            @RequestBody UnitDTO payload
    ) {
        ResponseData<UnitDTO> responseData = unitService.updateUnit(id, payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @DeleteMapping("/unit/{id}")
    public ResponseEntity<ResponseData<Void>> deleteUnitById(@PathVariable(name = "id") Integer id) {
        ResponseData<Void> responseData = unitService.deleteUnitById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @GetMapping("/unit/{id}")
    public ResponseEntity<ResponseData<UnitDTO>> getUnitById(@PathVariable(name = "id") Integer id) {
        ResponseData<UnitDTO> responseData = unitService.getUnitById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }
}
