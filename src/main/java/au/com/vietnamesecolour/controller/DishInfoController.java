package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.dto.DishInfoDTO;
import au.com.vietnamesecolour.service.DishInfoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/private/dishes")
@RequiredArgsConstructor
@Validated
public class DishInfoController {
    
    private final DishInfoService dishInfoService;

    @GetMapping
    public ResponseEntity<ResponseData<ResponsePage<DishInfoDTO>>> findDishInfo(
            @RequestParam(name = "dishName", required = false, defaultValue = "") String dishName,
            @RequestParam(name = "dishGroupName", required = false, defaultValue = "") String dishGroupName,
            @Valid @Min(value = 1, message = "Page must be start from 1") @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @Valid @Min(value = 1, message = "Page size must be start from 1") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        ResponseData<ResponsePage<DishInfoDTO>> responseData = dishInfoService.findDishInfo(dishName, dishGroupName, page, pageSize);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ResponseData<DishInfoDTO>> createDishInfo(
            @RequestPart DishInfoDTO dishInfoDTO,
            @RequestPart(name = "image") MultipartFile imageFile
    ) {
        ResponseData<DishInfoDTO> responseData = dishInfoService.createDishInfo(dishInfoDTO, imageFile);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

}
