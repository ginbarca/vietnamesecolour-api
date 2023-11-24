package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.dto.UnitDTO;

public interface UnitService {

    ResponseData<UnitDTO> createUnit(UnitDTO payload);
    ResponseData<UnitDTO> updateUnit(UnitDTO payload);
    ResponseData<Void> deleteUnitById(Integer id);
    ResponseData<UnitDTO> getUnitById(Integer id);
    ResponsePage<UnitDTO> findUnit(String unitName, Integer page, Integer pageSize);
}
