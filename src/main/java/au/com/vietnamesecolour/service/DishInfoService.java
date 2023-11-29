package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.dto.DishInfoDTO;
import org.springframework.web.multipart.MultipartFile;

public interface DishInfoService {

    ResponseData<DishInfoDTO> createDishInfo(DishInfoDTO payload, MultipartFile imageFile);
    ResponseData<DishInfoDTO> updateDishInfo(Integer id, DishInfoDTO payload, MultipartFile imageFile);
    ResponseData<Void> deleteDishInfoById(Integer id);
    ResponseData<DishInfoDTO> getDishInfoById(Integer id);
    ResponseData<ResponsePage<DishInfoDTO>> findDishInfo(String dishName, String dishGroupName, Integer page, Integer pageSize);

}
