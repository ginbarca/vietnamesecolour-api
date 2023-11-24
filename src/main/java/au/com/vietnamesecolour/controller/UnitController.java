package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.dto.UnitDTO;
import au.com.vietnamesecolour.service.UnitService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ResponsePage<UnitDTO>> findUnit(
            @RequestParam(name = "unitName", required = false, defaultValue = "") String unitName,
            @Valid @Min(value = 1, message = "Số trang phải bắt đầu từ 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Số bản ghi trên 1 trang phải bắt đầu từ 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        ResponsePage<UnitDTO> responsePage = unitService.findUnit(unitName, page, pageSize);
        return ResponseEntity.ok(responsePage);
    }

    @PostMapping
    public ResponseEntity<ResponseData<UnitDTO>> createUnit(@RequestBody UnitDTO payload) {
        ResponseData<UnitDTO> responseData = unitService.createUnit(payload);
        return ResponseEntity.ok(responseData);
    }

    @PatchMapping
    public ResponseEntity<ResponseData<UnitDTO>> updateUnit(@RequestBody UnitDTO payload) {
        ResponseData<UnitDTO> responseData = unitService.updateUnit(payload);
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping
    public ResponseEntity<ResponseData<Void>> deleteUnitById(
            @RequestParam(name = "id") Integer id
    ) {
        ResponseData<Void> responseData = unitService.deleteUnitById(id);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<UnitDTO>> getUnitById(@Valid @NotNull @PathVariable(name = "id") Integer id) {
        ResponseData<UnitDTO> responseData = unitService.getUnitById(id);
        return ResponseEntity.ok(responseData);
    }
}
