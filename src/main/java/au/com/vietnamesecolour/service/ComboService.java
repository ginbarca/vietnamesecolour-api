package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.dto.ComboDTO;

public interface ComboService {

    ResponseData<ComboDTO> createCombo(ComboDTO payload);
    ResponseData<ComboDTO> updateCombo(Integer id, ComboDTO payload);
    ResponseData<Void> deleteComboById(Integer id);
    ResponseData<ComboDTO> getComboById(Integer id);
    ResponseData<ResponsePage<ComboDTO>> findCombo(String comboName, Integer page, Integer pageSize);
}
