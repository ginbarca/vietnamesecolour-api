package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.dto.DishGroupDTO;
import au.com.vietnamesecolour.service.DishGroupService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/private/dish-groups")
@RequiredArgsConstructor
@Validated
public class DishGroupController {

    private final DishGroupService dishGroupService;

    @GetMapping
    public ResponseEntity<ResponseData<ResponsePage<DishGroupDTO>>> findDishGroup(
            @RequestParam(name = "dishGroupName", required = false, defaultValue = "") String dishGroupName,
            @Valid @Min(value = 1, message = "Số trang phải bắt đầu từ 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Số bản ghi trên 1 trang phải bắt đầu từ 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        ResponseData<ResponsePage<DishGroupDTO>> responseData = dishGroupService.findDishGroup(dishGroupName, page, pageSize);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping
    public ResponseEntity<ResponseData<DishGroupDTO>> createDishGroup(@RequestBody DishGroupDTO payload) {
        ResponseData<DishGroupDTO> responseData = dishGroupService.createDishGroup(payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PatchMapping
    public ResponseEntity<ResponseData<DishGroupDTO>> updateDishGroup(@RequestBody DishGroupDTO payload) {
        ResponseData<DishGroupDTO> responseData = dishGroupService.updateDishGroup(payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteDishGroupById(@Valid @NotNull @PathVariable(name = "id") Integer id) {
        ResponseData<Void> responseData = dishGroupService.deleteDishGroupById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<DishGroupDTO>> getDishGroupById(@Valid @NotNull @PathVariable(name = "id") Integer id) {
        ResponseData<DishGroupDTO> responseData = dishGroupService.getDishGroupById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }
}
