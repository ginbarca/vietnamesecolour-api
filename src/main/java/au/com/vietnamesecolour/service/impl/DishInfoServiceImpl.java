package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.dto.DishInfoDTO;
import au.com.vietnamesecolour.entity.DishInfo;
import au.com.vietnamesecolour.mapper.DishInfoMapper;
import au.com.vietnamesecolour.repos.DishGroupRepository;
import au.com.vietnamesecolour.repos.DishInfoRepository;
import au.com.vietnamesecolour.repos.UnitRepository;
import au.com.vietnamesecolour.service.DishInfoService;
import au.com.vietnamesecolour.service.FileUploaderAbstract;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DishInfoServiceImpl implements DishInfoService {

    private final DishInfoRepository dishInfoRepository;
    private final DishGroupRepository groupRepository;
    private final UnitRepository unitRepository;


    private final String uploadDir;
    private Path rootLocation;

    @Autowired
    public DishInfoServiceImpl(DishInfoRepository dishInfoRepository, DishGroupRepository groupRepository, UnitRepository unitRepository, @Value("${application.upload-dir}") String uploadDir) {
        this.dishInfoRepository = dishInfoRepository;
        this.groupRepository = groupRepository;
        this.unitRepository = unitRepository;
        this.uploadDir = uploadDir;
        this.rootLocation = Paths.get(uploadDir);
    }

    @Override
    public ResponseData<DishInfoDTO> createDishInfo(DishInfoDTO payload, MultipartFile imageFile) {
        FileUploaderAbstract<DishInfoDTO, DishInfo> uploader = new DishInfoCreation(uploadDir, rootLocation);
        ResponseData<DishInfoDTO> responseData = uploader.uploadHandler(payload, null, imageFile);
        return responseData;
    }

    @Override
    public ResponseData<DishInfoDTO> updateDishInfo(Integer id, DishInfoDTO payload, MultipartFile imageFile) {
        ResponseData<DishInfoDTO> responseData;
        Optional<DishInfo> dishFindResult = dishInfoRepository.findById(id);
        if (dishFindResult.isPresent()) {
            DishInfo dishInfo = dishFindResult.get();
            FileUploaderAbstract<DishInfoDTO, DishInfo> uploader = new DishInfoUpdate(uploadDir, rootLocation);
            responseData = uploader.uploadHandler(payload, dishInfo, imageFile);
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<Void> deleteDishInfoById(Integer id) {
        boolean isDishExist = dishInfoRepository.existsById(id);
        ResponseData<Void> responseData;
        if (isDishExist) {
            dishInfoRepository.deleteById(id);
            responseData = new ResponseData<>(ResponseStatusCode.OK.getCode(), ResponseStatusCode.OK.getDescription());
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<DishInfoDTO> getDishInfoById(Integer id) {
        ResponseData<DishInfoDTO> responseData;
        Optional<DishInfo> dishInfo = dishInfoRepository.findById(id);
        if (dishInfo.isPresent()) {
            responseData = new ResponseData<>();
            responseData.setData(DishInfoMapper.INSTANCE.entityToDTO(dishInfo.get()));
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<ResponsePage<DishInfoDTO>> findDishInfo(String dishName, String dishGroupName, Integer page, Integer pageSize) {
        ResponseData<ResponsePage<DishInfoDTO>> responseData = new ResponseData<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<DishInfo> dishPage = dishInfoRepository.findDishInfoByDishNameAndDishGroup(dishName, dishGroupName, pageable);

        List<DishInfoDTO> dishInfoDTOS = dishPage.getContent().stream().map(DishInfoMapper.INSTANCE::entityToDTO).toList();

        ResponsePage<DishInfoDTO> responsePage = new ResponsePage<>(
                dishPage.getNumber() + 1,
                dishPage.getSize(),
                dishPage.getTotalElements(),
                dishPage.getTotalPages(),
                dishInfoDTOS
        );
        responseData.setData(responsePage);
        return responseData;
    }

    private class DishInfoCreation extends FileUploaderAbstract<DishInfoDTO, DishInfo> {

        protected DishInfoCreation(String uploadDir, Path rootLocation) {
            super(uploadDir, rootLocation);
        }

        @Override
        public ResponseData<DishInfoDTO> process(DishInfoDTO payload, DishInfo entity, MultipartFile file) {
            entity = DishInfo
                    .builder()
                    .dishName(payload.getDishName())
                    .price(payload.getPrice())
                    .dishDescription(payload.getDishDescription())
                    .dishImagePath(this.rootLocation.resolve(file.getOriginalFilename()).toString())
                    .dishImageName(file.getOriginalFilename())
                    .dishGroup(groupRepository.findById(payload.getDishGroupId()).get())
                    .unit(unitRepository.findById(payload.getUnitId()).get())
                    .build();
            DishInfo savedDish = dishInfoRepository.save(entity);
            DishInfoDTO dishInfoDTO = DishInfoMapper.INSTANCE.entityToDTO(savedDish);
            ResponseData<DishInfoDTO> responseData = new ResponseData<>(ResponseStatusCode.CREATED.getCode(), ResponseStatusCode.CREATED.getDescription());
            responseData.setData(dishInfoDTO);
            return responseData;
        }
    }

    private class DishInfoUpdate extends FileUploaderAbstract<DishInfoDTO, DishInfo> {

        protected DishInfoUpdate(String uploadDir, Path rootLocation) {
            super(uploadDir, rootLocation);
        }

        @Override
        public ResponseData<DishInfoDTO> process(DishInfoDTO payload, DishInfo entity, MultipartFile file) {
            entity.setDishName(payload.getDishName());
            entity.setPrice(payload.getPrice());
            entity.setDishDescription(payload.getDishDescription());
            entity.setDishImagePath(this.rootLocation.resolve(file.getOriginalFilename()).toString());
            entity.setDishImageName(file.getOriginalFilename());
            entity.setDishGroup(groupRepository.findById(payload.getDishGroupId()).get());
            entity.setUnit(unitRepository.findById(payload.getUnitId()).get());

            DishInfo savedDish = dishInfoRepository.save(entity);
            DishInfoDTO dishInfoDTO = DishInfoMapper.INSTANCE.entityToDTO(savedDish);
            ResponseData<DishInfoDTO> responseData = new ResponseData<>();
            responseData.setData(dishInfoDTO);
            return responseData;
        }
    }
}
