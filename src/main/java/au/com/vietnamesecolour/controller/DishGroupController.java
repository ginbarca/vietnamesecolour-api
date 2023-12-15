package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.config.data.ViewMode;
import au.com.vietnamesecolour.dto.DishGroupDTO;
import au.com.vietnamesecolour.service.DishGroupService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class DishGroupController {

    private final DishGroupService dishGroupService;

    @JsonView({ViewMode.Public.class})
    @GetMapping("/api/v1/public/menu")
    public ResponseEntity<ResponseData<List<DishGroupDTO>>> listAllDishGroups() {
        ResponseData<List<DishGroupDTO>> responseData = dishGroupService.listAllDishGroups();
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @JsonView({ViewMode.Private.class})
    @GetMapping("/api/v1/private/dish-groups")
    public ResponseEntity<ResponseData<ResponsePage<DishGroupDTO>>> findDishGroup(
            @RequestParam(name = "dishGroupName", required = false, defaultValue = "") String dishGroupName,
            @Valid @Min(value = 1, message = "Page must be start from 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Page size must be start from 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        ResponseData<ResponsePage<DishGroupDTO>> responseData = dishGroupService.findDishGroup(dishGroupName, page, pageSize);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping("/api/v1/private/dish-group")
    public ResponseEntity<ResponseData<DishGroupDTO>> createDishGroup(@Valid @RequestBody DishGroupDTO payload) {
        ResponseData<DishGroupDTO> responseData = dishGroupService.createDishGroup(payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PatchMapping("/api/v1/private/dish-group/{id}")
    public ResponseEntity<ResponseData<DishGroupDTO>> updateDishGroup(
            @PathVariable(name = "id") Integer id,
            @Valid @RequestBody DishGroupDTO payload
    ) {
        ResponseData<DishGroupDTO> responseData = dishGroupService.updateDishGroup(id, payload);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @DeleteMapping("/api/v1/private/dish-group/{id}")
    public ResponseEntity<ResponseData<Void>> deleteDishGroupById(@PathVariable(name = "id") Integer id) {
        ResponseData<Void> responseData = dishGroupService.deleteDishGroupById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @GetMapping("/api/v1/private/dish-group/{id}")
    public ResponseEntity<ResponseData<DishGroupDTO>> getDishGroupById(@PathVariable(name = "id") Integer id) {
        ResponseData<DishGroupDTO> responseData = dishGroupService.getDishGroupById(id);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }
}
