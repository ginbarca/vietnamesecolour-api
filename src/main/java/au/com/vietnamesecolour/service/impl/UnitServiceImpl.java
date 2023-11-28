package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.constant.DateConstant;
import au.com.vietnamesecolour.dto.UnitDTO;
import au.com.vietnamesecolour.entity.Unit;
import au.com.vietnamesecolour.mapper.UnitMapper;
import au.com.vietnamesecolour.repos.UnitRepository;
import au.com.vietnamesecolour.service.UnitService;
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
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepo;

    @Override
    public ResponseData<UnitDTO> createUnit(UnitDTO payload) {
        Unit unit = Unit.builder().unitName(payload.getUnitName()).build();
        Unit savedUnit = unitRepo.save(unit);
        UnitDTO dto = UnitDTO.builder()
                .id(savedUnit.getId())
                .unitName(savedUnit.getUnitName())
                .createdBy(savedUnit.getCreatedBy().getUsername())
                .updatedBy(savedUnit.getUpdatedBy().getUsername())
                .createdDate(DateTimeUtils.formatDate(savedUnit.getCreatedDate(), DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS))
                .updatedDate(DateTimeUtils.formatDate(savedUnit.getUpdatedDate(), DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS))
                .build();
        ResponseData<UnitDTO> responseData = new ResponseData<>(ResponseStatusCode.CREATED.getCode(), ResponseStatusCode.CREATED.getDescription());
        responseData.setData(dto);
        return responseData;
    }

    @Override
    public ResponseData<UnitDTO> updateUnit(Integer id, UnitDTO payload) {
        Optional<Unit> unit = unitRepo.findById(id);
        ResponseData<UnitDTO> responseData;
        if (unit.isPresent()) {
            unit.get().setUnitName(payload.getUnitName());
            Unit savedUnit = unitRepo.save(unit.get());
            UnitDTO dto = UnitDTO.builder()
                    .id(savedUnit.getId())
                    .unitName(savedUnit.getUnitName())
                    .createdBy(savedUnit.getCreatedBy().getUsername())
                    .updatedBy(savedUnit.getUpdatedBy().getUsername())
                    .createdDate(DateTimeUtils.formatDate(savedUnit.getCreatedDate(), DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS))
                    .updatedDate(DateTimeUtils.formatDate(savedUnit.getUpdatedDate(), DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS))
                    .build();
            responseData = new ResponseData<>();
            responseData.setData(dto);
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<Void> deleteUnitById(Integer id) {
        Optional<Unit> unit = unitRepo.findById(id);
        ResponseData<Void> responseData;
        if (unit.isPresent()) {
            unitRepo.deleteById(id);
            responseData = new ResponseData<>(ResponseStatusCode.OK.getCode(), ResponseStatusCode.OK.getDescription());
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<UnitDTO> getUnitById(Integer id) {
        ResponseData<UnitDTO> responseData;
        Optional<Unit> unit = unitRepo.findById(id);
        if (unit.isPresent()) {
            Unit u = unit.get();
            responseData = new ResponseData<>();
            responseData.setData(
                    UnitDTO.builder()
                            .id(u.getId())
                            .unitName(u.getUnitName())
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
    public ResponseData<ResponsePage<UnitDTO>> findUnit(String unitName, Integer page, Integer pageSize) {
        ResponseData<ResponsePage<UnitDTO>> responseData = new ResponseData<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Unit> unitPage = unitRepo.findUnitByUnitName(unitName, pageable);

        List<UnitDTO> unitDTOS = unitPage.getContent().stream().map(UnitMapper.INSTANCE::entityToDTO).toList();

        ResponsePage<UnitDTO> responsePage = new ResponsePage<>(
                unitPage.getNumber() + 1,
                unitPage.getSize(),
                unitPage.getTotalElements(),
                unitPage.getTotalPages(),
                unitDTOS
        );
        responseData.setData(responsePage);
        return responseData;
    }

    @Override
    public Boolean isUnitExist(Integer id) {
        return unitRepo.existsById(id);
    }

}
