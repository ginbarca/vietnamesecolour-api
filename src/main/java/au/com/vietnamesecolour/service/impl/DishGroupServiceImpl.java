package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.constant.DateConstant;
import au.com.vietnamesecolour.dto.DishGroupDTO;
import au.com.vietnamesecolour.entity.DishGroup;
import au.com.vietnamesecolour.mapper.DishGroupMapper;
import au.com.vietnamesecolour.repos.DishGroupRepository;
import au.com.vietnamesecolour.service.DishGroupService;
import au.com.vietnamesecolour.utils.DateTimeUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DishGroupServiceImpl implements DishGroupService {

    private final DishGroupRepository dishGroupRepo;

    @Override
    public ResponseData<DishGroupDTO> createDishGroup(DishGroupDTO payload) {
        DishGroup dishGroup = DishGroup.builder().dishGroupName(payload.getDishGroupName()).build();
        DishGroup savedDishGroup = dishGroupRepo.save(dishGroup);
        DishGroupDTO dto = DishGroupDTO.builder()
                .id(savedDishGroup.getId())
                .dishGroupName(savedDishGroup.getDishGroupName())
                .createdBy(savedDishGroup.getCreatedBy().getUsername())
                .updatedBy(savedDishGroup.getUpdatedBy().getUsername())
                .createdDate(DateTimeUtils.formatDate(savedDishGroup.getCreatedDate(), DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS))
                .updatedDate(DateTimeUtils.formatDate(savedDishGroup.getUpdatedDate(), DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS))
                .build();
        ResponseData<DishGroupDTO> responseData = new ResponseData<>(ResponseStatusCode.CREATED.getCode(), ResponseStatusCode.CREATED.getDescription());
        responseData.setData(dto);
        return responseData;
    }

    @Override
    public ResponseData<DishGroupDTO> updateDishGroup(Integer id, DishGroupDTO payload) {
        Optional<DishGroup> dishGroup = dishGroupRepo.findById(id);
        ResponseData<DishGroupDTO> responseData;
        if (dishGroup.isPresent()) {
            dishGroup.get().setDishGroupName(payload.getDishGroupName());
            DishGroup savedDishGroup = dishGroupRepo.save(dishGroup.get());
            DishGroupDTO dto = DishGroupDTO.builder()
                    .id(savedDishGroup.getId())
                    .dishGroupName(savedDishGroup.getDishGroupName())
                    .createdBy(savedDishGroup.getCreatedBy().getUsername())
                    .updatedBy(savedDishGroup.getUpdatedBy().getUsername())
                    .createdDate(DateTimeUtils.formatDate(savedDishGroup.getCreatedDate(), DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS))
                    .updatedDate(DateTimeUtils.formatDate(savedDishGroup.getUpdatedDate(), DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS))
                    .build();
            responseData = new ResponseData<>();
            responseData.setData(dto);
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<Void> deleteDishGroupById(Integer id) {
        Optional<DishGroup> dishGroup = dishGroupRepo.findById(id);
        ResponseData<Void> responseData;
        if (dishGroup.isPresent()) {
            dishGroupRepo.deleteById(id);
            responseData = new ResponseData<>(ResponseStatusCode.OK.getCode(), ResponseStatusCode.OK.getDescription());
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<DishGroupDTO> getDishGroupById(Integer id) {
        ResponseData<DishGroupDTO> responseData;
        Optional<DishGroup> dishGroup = dishGroupRepo.findById(id);
        if (dishGroup.isPresent()) {
            DishGroup u = dishGroup.get();
            responseData = new ResponseData<>();
            responseData.setData(
                    DishGroupDTO.builder()
                            .id(u.getId())
                            .dishGroupName(u.getDishGroupName())
                            .createdBy(u.getCreatedBy().getUsername())
                            .updatedBy(u.getUpdatedBy().getUsername())
                            .createdDate(DateTimeUtils.formatDate(u.getCreatedDate(), DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS))
                            .updatedDate(DateTimeUtils.formatDate(u.getUpdatedDate(), DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS))
                            .build()
            );
        } else {
            responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        }
        return responseData;
    }

    @Override
    public ResponseData<ResponsePage<DishGroupDTO>> findDishGroup(String dishGroupName, Integer page, Integer pageSize) {
        ResponseData<ResponsePage<DishGroupDTO>> responseData = new ResponseData<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<DishGroup> dishGroupPage = dishGroupRepo.findDishGroupByDishGroupName(dishGroupName, pageable);

        List<DishGroupDTO> dishGroupDTOS = dishGroupPage.getContent().stream().map(DishGroupMapper.INSTANCE::entityToDTO).toList();

        ResponsePage<DishGroupDTO> responsePage = new ResponsePage<>(
                dishGroupPage.getNumber() + 1,
                dishGroupPage.getSize(),
                dishGroupPage.getTotalElements(),
                dishGroupPage.getTotalPages(),
                dishGroupDTOS
        );
        responseData.setData(responsePage);
        return responseData;
    }

    @Override
    public ResponseData<List<DishGroupDTO>> listAllDishGroups() {
        ResponseData<List<DishGroupDTO>> responseData = new ResponseData<>();
        List<DishGroupDTO> dtos = dishGroupRepo.findAll().stream().map(DishGroupMapper.INSTANCE::entityToDTO).toList();
        responseData.setData(dtos);
        return responseData;
    }
}
