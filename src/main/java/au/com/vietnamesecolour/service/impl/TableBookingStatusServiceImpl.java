package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.constant.DateConstant;
import au.com.vietnamesecolour.dto.TableBookingStatusDTO;
import au.com.vietnamesecolour.entity.TableBookingStatus;
import au.com.vietnamesecolour.mapper.TableBookingStatusMapper;
import au.com.vietnamesecolour.repos.TableBookingStatusRepository;
import au.com.vietnamesecolour.service.TableBookingStatusService;
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
public class TableBookingStatusServiceImpl implements TableBookingStatusService {

    private final TableBookingStatusRepository bookingStatusRepo;

    @Override
    public ResponseData<TableBookingStatusDTO> createTableBookingStatus(TableBookingStatusDTO payload) {
        TableBookingStatus bookingStatus = TableBookingStatus.builder().bookingStatusName(payload.getBookingStatusName()).build();
        TableBookingStatus savedTableBookingStatus = bookingStatusRepo.save(bookingStatus);
        TableBookingStatusDTO dto = TableBookingStatusMapper.INSTANCE.entityToDTO(savedTableBookingStatus);
        ResponseData<TableBookingStatusDTO> responseData = new ResponseData<>(ResponseStatusCode.CREATED.getCode(), ResponseStatusCode.CREATED.getDescription());
        responseData.setData(dto);
        return responseData;
    }

    @Override
    public ResponseData<TableBookingStatusDTO> updateTableBookingStatus(Integer id, TableBookingStatusDTO payload) {
        Optional<TableBookingStatus> bookingStatus = bookingStatusRepo.findById(id);
        ResponseData<TableBookingStatusDTO> responseData;
        if (bookingStatus.isPresent()) {
            bookingStatus.get().setBookingStatusName(payload.getBookingStatusName());
            TableBookingStatus savedTableBookingStatus = bookingStatusRepo.save(bookingStatus.get());
            TableBookingStatusDTO dto = TableBookingStatusMapper.INSTANCE.entityToDTO(savedTableBookingStatus);
            responseData = new ResponseData<>();
            responseData.setData(dto);
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<Void> deleteTableBookingStatusById(Integer id) {
        Optional<TableBookingStatus> bookingStatus = bookingStatusRepo.findById(id);
        ResponseData<Void> responseData;
        if (bookingStatus.isPresent()) {
            bookingStatusRepo.deleteById(id);
            responseData = new ResponseData<>(ResponseStatusCode.OK.getCode(), ResponseStatusCode.OK.getDescription());
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<TableBookingStatusDTO> getTableBookingStatusById(Integer id) {
        ResponseData<TableBookingStatusDTO> responseData;
        Optional<TableBookingStatus> bookingStatus = bookingStatusRepo.findById(id);
        if (bookingStatus.isPresent()) {
            TableBookingStatus u = bookingStatus.get();
            responseData = new ResponseData<>();
            responseData.setData(TableBookingStatusMapper.INSTANCE.entityToDTO(u));
        } else {
            responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        }
        return responseData;
    }

    @Override
    public ResponseData<ResponsePage<TableBookingStatusDTO>> findTableBookingStatus(String bookingStatusName, Integer page, Integer pageSize) {
        ResponseData<ResponsePage<TableBookingStatusDTO>> responseData = new ResponseData<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<TableBookingStatus> bookingStatusPage = bookingStatusRepo.findTableBookingStatusByBookingStatusName(bookingStatusName, pageable);

        List<TableBookingStatusDTO> bookingStatusDTOS = bookingStatusPage.getContent().stream().map(TableBookingStatusMapper.INSTANCE::entityToDTO).toList();

        ResponsePage<TableBookingStatusDTO> responsePage = new ResponsePage<>(
                bookingStatusPage.getNumber() + 1,
                bookingStatusPage.getSize(),
                bookingStatusPage.getTotalElements(),
                bookingStatusPage.getTotalPages(),
                bookingStatusDTOS
        );
        responseData.setData(responsePage);
        return responseData;
    }
}
