package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.dto.DishGroupDTO;

public interface DishGroupService {

    ResponseData<DishGroupDTO> createDishGroup(DishGroupDTO payload);
    ResponseData<DishGroupDTO> updateDishGroup(DishGroupDTO payload);
    ResponseData<Void> deleteDishGroupById(Integer id);
    ResponseData<DishGroupDTO> getDishGroupById(DishGroupDTO payload);
    ResponsePage<DishGroupDTO> findDishGroup(DishGroupDTO payload);
}
