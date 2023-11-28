package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.dto.DishInfoDTO;

public interface DishInfoService {

    ResponseData<DishInfoDTO> createDishInfo(DishInfoDTO payload);
    ResponseData<DishInfoDTO> updateDishInfo(DishInfoDTO payload);
    ResponseData<Void> deleteDishInfoById(Integer id);
    ResponseData<DishInfoDTO> getDishInfoById(DishInfoDTO payload);
    ResponsePage<DishInfoDTO> findDishInfo(DishInfoDTO payload);
}
