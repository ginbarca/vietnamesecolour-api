package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.constant.DateConstant;
import au.com.vietnamesecolour.dto.TableBookingDTO;
import au.com.vietnamesecolour.entity.TableBooking;
import au.com.vietnamesecolour.mapper.TableBookingMapper;
import au.com.vietnamesecolour.repos.TableBookingRepository;
import au.com.vietnamesecolour.repos.TableBookingStatusRepository;
import au.com.vietnamesecolour.service.TableBookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TableBookingServiceImpl implements TableBookingService {

    private final TableBookingRepository bookingRepository;
    private final TableBookingStatusRepository bookingStatusRepository;

    @Override
    public ResponseData<TableBookingDTO> createTableBooking(TableBookingDTO payload) throws ParseException {
        TableBooking tableBooking = TableBooking
                .builder()
                .customerName(payload.getCustomerName())
                .mobileNumber(payload.getMobileNumber())
                .email(payload.getEmail())
                .numberOfPeople(payload.getNumberOfPeople())
                .bookingDate(DateUtils.parseDate(payload.getBookingDate(), DateConstant.STR_PLAN_DD_MM_YYYY))
                .bookingTime(payload.getBookingTime())
                .note(payload.getNote())
                .bookingStatus(bookingStatusRepository.findById(payload.getBookingStatusId()).get())
                .build();
        TableBooking savedBooking = bookingRepository.save(tableBooking);
        TableBookingDTO dto = TableBookingMapper.INSTANCE.entityToDTO(savedBooking);
        ResponseData<TableBookingDTO> responseData = new ResponseData<>(ResponseStatusCode.CREATED.getCode(), ResponseStatusCode.CREATED.getDescription());
        responseData.setData(dto);
        return responseData;
    }

    @Override
    public ResponseData<TableBookingDTO> updateTableBooking(Integer id, TableBookingDTO payload) throws ParseException {
        Optional<TableBooking> bookingSearch = bookingRepository.findById(id);
        ResponseData<TableBookingDTO> responseData;
        if (bookingSearch.isPresent()) {
            TableBooking tableBooking = bookingSearch.get();
            tableBooking.setCustomerName(payload.getCustomerName());
            tableBooking.setMobileNumber(payload.getMobileNumber());
            tableBooking.setEmail(payload.getEmail());
            tableBooking.setNumberOfPeople(payload.getNumberOfPeople());
            tableBooking.setBookingDate(DateUtils.parseDate(payload.getBookingDate(), DateConstant.STR_PLAN_DD_MM_YYYY));
            tableBooking.setBookingTime(payload.getBookingTime());
            tableBooking.setNote(payload.getNote());
            tableBooking.setBookingStatus(bookingStatusRepository.findById(payload.getBookingStatusId()).get());

            TableBooking savedBooking = bookingRepository.save(tableBooking);
            TableBookingDTO dto = TableBookingMapper.INSTANCE.entityToDTO(savedBooking);
            responseData = new ResponseData<>();
            responseData.setData(dto);
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<Void> deleteTableBookingById(Integer id) {
        boolean isBookingExist = bookingRepository.existsById(id);
        ResponseData<Void> responseData;
        if (isBookingExist) {
            bookingRepository.deleteById(id);
            responseData = new ResponseData<>(ResponseStatusCode.OK.getCode(), ResponseStatusCode.OK.getDescription());
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<TableBookingDTO> getTableBookingById(Integer id) {
        Optional<TableBooking> bookingSearch = bookingRepository.findById(id);
        ResponseData<TableBookingDTO> responseData;
        if (bookingSearch.isPresent()) {
            responseData = new ResponseData<>();
            responseData.setData(TableBookingMapper.INSTANCE.entityToDTO(bookingSearch.get()));
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<ResponsePage<TableBookingDTO>> findTableBooking(String customerName, String mobileNumber, String email, String bookingDateFrom, String bookingDateTo, Integer page, Integer pageSize) throws ParseException {
        ResponseData<ResponsePage<TableBookingDTO>> responseData = new ResponseData<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<TableBooking> bookingPage = bookingRepository.findTableBooking(
                customerName,
                mobileNumber,
                email,
                Objects.isNull(bookingDateFrom) ? null : DateUtils.parseDate(bookingDateFrom, DateConstant.STR_PLAN_DD_MM_YYYY),
                Objects.isNull(bookingDateTo) ? null : DateUtils.parseDate(bookingDateTo, DateConstant.STR_PLAN_DD_MM_YYYY),
                pageable
        );

        List<TableBookingDTO> bookingDTOS = bookingPage.getContent().stream().map(TableBookingMapper.INSTANCE::entityToDTO).toList();

        ResponsePage<TableBookingDTO> responsePage = new ResponsePage<>(
                bookingPage.getNumber() + 1,
                bookingPage.getSize(),
                bookingPage.getTotalElements(),
                bookingPage.getTotalPages(),
                bookingDTOS
        );
        responseData.setData(responsePage);
        return responseData;
    }
}
