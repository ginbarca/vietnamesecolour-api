package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.dto.DishInfoDTO;
import au.com.vietnamesecolour.entity.DishInfo;
import au.com.vietnamesecolour.mapper.DishInfoMapper;
import au.com.vietnamesecolour.repos.DishGroupRepository;
import au.com.vietnamesecolour.repos.DishInfoRepository;
import au.com.vietnamesecolour.repos.UnitRepository;
import au.com.vietnamesecolour.service.DishInfoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

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
        ResponseData<DishInfoDTO> responseData;
        if (uploadDir.trim().length() == 0) {
            responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "File upload location can not be empty");
            return responseData;
        }
        try {
            if (imageFile.isEmpty()) {
                responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "Failed to store empty file");
                return responseData;
            }
            Path destinationFile = this.rootLocation
                    .resolve(
                            Paths.get(Objects.requireNonNull(imageFile.getOriginalFilename()))
                    )
                    .normalize()
                    .toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "Cannot store file outside current directory");
                return responseData;
            }
            try (InputStream inputStream = imageFile.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                DishInfo dishInfo = DishInfo
                        .builder()
                        .dishName(payload.getDishName())
                        .price(payload.getPrice())
                        .dishDescription(payload.getDishDescription())
                        .dishImagePath(this.rootLocation.resolve(imageFile.getOriginalFilename()).toString())
                        .dishImageName(imageFile.getOriginalFilename())
                        .dishGroup(groupRepository.findById(payload.getDishGroupId()).get())
                        .unit(unitRepository.findById(payload.getUnitId()).get())
                        .build();
                DishInfo savedDish = dishInfoRepository.save(dishInfo);
                DishInfoDTO dishInfoDTO = DishInfoMapper.INSTANCE.entityToDTO(savedDish);
                responseData = new ResponseData<>();
                responseData.setData(dishInfoDTO);
            }
        } catch (IOException e) {
            responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "Failed to store file");
            return responseData;
        }
        return responseData;
    }

    @Override
    public ResponseData<DishInfoDTO> updateDishInfo(Integer id, DishInfoDTO payload) {
        return null;
    }

    @Override
    public ResponseData<Void> deleteDishInfoById(Integer id) {
        return null;
    }

    @Override
    public ResponseData<DishInfoDTO> getDishInfoById(Integer id) {
        return null;
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

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
