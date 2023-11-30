package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.dto.PaymentStatusDTO;
import au.com.vietnamesecolour.entity.PaymentStatus;
import au.com.vietnamesecolour.mapper.PaymentStatusMapper;
import au.com.vietnamesecolour.repos.PaymentStatusRepository;
import au.com.vietnamesecolour.service.PaymentStatusService;
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
public class PaymentStatusServiceImpl implements PaymentStatusService {

    private final PaymentStatusRepository paymentStatusRepo;

    @Override
    public ResponseData<PaymentStatusDTO> createPaymentStatus(PaymentStatusDTO payload) {
        PaymentStatus paymentStatus = PaymentStatus.builder().paymentStatusName(payload.getPaymentStatusName()).build();
        PaymentStatus savedPaymentStatus = paymentStatusRepo.save(paymentStatus);
        PaymentStatusDTO dto = PaymentStatusMapper.INSTANCE.entityToDTO(savedPaymentStatus);
        ResponseData<PaymentStatusDTO> responseData = new ResponseData<>(ResponseStatusCode.CREATED.getCode(), ResponseStatusCode.CREATED.getDescription());
        responseData.setData(dto);
        return responseData;
    }

    @Override
    public ResponseData<PaymentStatusDTO> updatePaymentStatus(Integer id, PaymentStatusDTO payload) {
        Optional<PaymentStatus> paymentStatus = paymentStatusRepo.findById(id);
        ResponseData<PaymentStatusDTO> responseData;
        if (paymentStatus.isPresent()) {
            paymentStatus.get().setPaymentStatusName(payload.getPaymentStatusName());
            PaymentStatus savedPaymentStatus = paymentStatusRepo.save(paymentStatus.get());
            PaymentStatusDTO dto = PaymentStatusMapper.INSTANCE.entityToDTO(savedPaymentStatus);
            responseData = new ResponseData<>();
            responseData.setData(dto);
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<Void> deletePaymentStatusById(Integer id) {
        Optional<PaymentStatus> paymentStatus = paymentStatusRepo.findById(id);
        ResponseData<Void> responseData;
        if (paymentStatus.isPresent()) {
            paymentStatusRepo.deleteById(id);
            responseData = new ResponseData<>(ResponseStatusCode.OK.getCode(), ResponseStatusCode.OK.getDescription());
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<PaymentStatusDTO> getPaymentStatusById(Integer id) {
        ResponseData<PaymentStatusDTO> responseData;
        Optional<PaymentStatus> paymentStatus = paymentStatusRepo.findById(id);
        if (paymentStatus.isPresent()) {
            PaymentStatus u = paymentStatus.get();
            responseData = new ResponseData<>();
            responseData.setData(PaymentStatusMapper.INSTANCE.entityToDTO(u));
        } else {
            responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        }
        return responseData;
    }

    @Override
    public ResponseData<ResponsePage<PaymentStatusDTO>> findPaymentStatus(String paymentStatusName, Integer page, Integer pageSize) {
        ResponseData<ResponsePage<PaymentStatusDTO>> responseData = new ResponseData<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<PaymentStatus> paymentStatusPage = paymentStatusRepo.findPaymentStatusByPaymentStatusName(paymentStatusName, pageable);

        List<PaymentStatusDTO> paymentStatusDTOS = paymentStatusPage.getContent().stream().map(PaymentStatusMapper.INSTANCE::entityToDTO).toList();

        ResponsePage<PaymentStatusDTO> responsePage = new ResponsePage<>(
                paymentStatusPage.getNumber() + 1,
                paymentStatusPage.getSize(),
                paymentStatusPage.getTotalElements(),
                paymentStatusPage.getTotalPages(),
                paymentStatusDTOS
        );
        responseData.setData(responsePage);
        return responseData;
    }
}
