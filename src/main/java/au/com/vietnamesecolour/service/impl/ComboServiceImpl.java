package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.dto.ComboDTO;
import au.com.vietnamesecolour.entity.Combo;
import au.com.vietnamesecolour.entity.DishInfo;
import au.com.vietnamesecolour.mapper.ComboMapper;
import au.com.vietnamesecolour.repos.ComboRepository;
import au.com.vietnamesecolour.repos.DishInfoRepository;
import au.com.vietnamesecolour.service.ComboService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ComboServiceImpl implements ComboService {

    private final ComboRepository comboRepo;
    private final DishInfoRepository dishInfoRepository;

    @Override
    public ResponseData<ComboDTO> createCombo(ComboDTO payload) {
        ResponseData<ComboDTO> responseData;
        Combo combo = Combo.builder()
                .comboName(payload.getComboName())
                .comboPrice(payload.getComboPrice())
                .build();
        List<DishInfo> dishInfos = new ArrayList<>();
        for (Integer id : payload.getDishIds()) {
            Optional<DishInfo> dishInfo = dishInfoRepository.findById(id);
            if (dishInfo.isEmpty()) {
                responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no dish with ID " + id);
                return responseData;
            }
            dishInfos.add(dishInfo.get());
        }
        combo.setDishInfos(dishInfos);
        Combo savedCombo = comboRepo.save(combo);
        ComboDTO dto = ComboMapper.INSTANCE.entityToDTO(savedCombo);
        responseData = new ResponseData<>(ResponseStatusCode.CREATED.getCode(), ResponseStatusCode.CREATED.getDescription());
        responseData.setData(dto);
        return responseData;
    }

    @Override
    public ResponseData<ComboDTO> updateCombo(Integer id, ComboDTO payload) {
        Optional<Combo> combo = comboRepo.findById(id);
        ResponseData<ComboDTO> responseData;
        if (combo.isPresent()) {
            combo.get().setComboName(payload.getComboName());
            combo.get().setComboPrice(payload.getComboPrice());
            List<DishInfo> dishInfos = new ArrayList<>();
            for (Integer dishId : payload.getDishIds()) {
                Optional<DishInfo> dishInfo = dishInfoRepository.findById(dishId);
                if (dishInfo.isEmpty()) {
                    responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no dish with ID " + id);
                    return responseData;
                }
                dishInfos.add(dishInfo.get());
            }
            combo.get().setDishInfos(dishInfos);
            Combo savedCombo = comboRepo.save(combo.get());
            ComboDTO dto = ComboMapper.INSTANCE.entityToDTO(savedCombo);
            responseData = new ResponseData<>();
            responseData.setData(dto);
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<Void> deleteComboById(Integer id) {
        Optional<Combo> combo = comboRepo.findById(id);
        ResponseData<Void> responseData;
        if (combo.isPresent()) {
            comboRepo.deleteById(id);
            responseData = new ResponseData<>(ResponseStatusCode.OK.getCode(), ResponseStatusCode.OK.getDescription());
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<ComboDTO> getComboById(Integer id) {
        ResponseData<ComboDTO> responseData;
        Optional<Combo> combo = comboRepo.findById(id);
        if (combo.isPresent()) {
            responseData = new ResponseData<>();
            responseData.setData(ComboMapper.INSTANCE.entityToDTO(combo.get()));
        } else {
            responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        }
        return responseData;
    }

    @Override
    public ResponseData<ResponsePage<ComboDTO>> findCombo(String comboName, Integer page, Integer pageSize) {
        ResponseData<ResponsePage<ComboDTO>> responseData = new ResponseData<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Combo> comboPage = comboRepo.findComboByComboName(comboName, pageable);

        List<ComboDTO> comboDTOS = comboPage.getContent().stream().map(ComboMapper.INSTANCE::entityToDTO).toList();

        ResponsePage<ComboDTO> responsePage = new ResponsePage<>(
                comboPage.getNumber() + 1,
                comboPage.getSize(),
                comboPage.getTotalElements(),
                comboPage.getTotalPages(),
                comboDTOS
        );
        responseData.setData(responsePage);
        return responseData;
    }
}
