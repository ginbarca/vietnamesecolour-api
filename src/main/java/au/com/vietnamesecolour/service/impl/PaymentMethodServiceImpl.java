package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.dto.PaymentMethodDTO;
import au.com.vietnamesecolour.entity.PaymentMethod;
import au.com.vietnamesecolour.mapper.PaymentMethodMapper;
import au.com.vietnamesecolour.repos.PaymentMethodRepository;
import au.com.vietnamesecolour.service.PaymentMethodService;
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
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepo;

    @Override
    public ResponseData<PaymentMethodDTO> createPaymentMethod(PaymentMethodDTO payload) {
        PaymentMethod paymentMethod = PaymentMethod.builder()
                .paymentMethodName(payload.getPaymentMethodName())
                .enabled(payload.getEnabled())
                .build();
        PaymentMethod savedPaymentMethod = paymentMethodRepo.save(paymentMethod);
        PaymentMethodDTO dto = PaymentMethodMapper.INSTANCE.entityToDTO(savedPaymentMethod);
        ResponseData<PaymentMethodDTO> responseData = new ResponseData<>(ResponseStatusCode.CREATED.getCode(), ResponseStatusCode.CREATED.getDescription());
        responseData.setData(dto);
        return responseData;
    }

    @Override
    public ResponseData<PaymentMethodDTO> updatePaymentMethod(Integer id, PaymentMethodDTO payload) {
        Optional<PaymentMethod> paymentMethod = paymentMethodRepo.findById(id);
        ResponseData<PaymentMethodDTO> responseData;
        if (paymentMethod.isPresent()) {
            paymentMethod.get().setPaymentMethodName(payload.getPaymentMethodName());
            paymentMethod.get().setEnabled(payload.getEnabled());

            PaymentMethod savedPaymentMethod = paymentMethodRepo.save(paymentMethod.get());
            PaymentMethodDTO dto = PaymentMethodMapper.INSTANCE.entityToDTO(savedPaymentMethod);
            responseData = new ResponseData<>();
            responseData.setData(dto);
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<Void> deletePaymentMethodById(Integer id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodRepo.findById(id);
        ResponseData<Void> responseData;
        if (paymentMethod.isPresent()) {
            paymentMethodRepo.deleteById(id);
            responseData = new ResponseData<>(ResponseStatusCode.OK.getCode(), ResponseStatusCode.OK.getDescription());
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<PaymentMethodDTO> getPaymentMethodById(Integer id) {
        ResponseData<PaymentMethodDTO> responseData;
        Optional<PaymentMethod> paymentMethod = paymentMethodRepo.findById(id);
        if (paymentMethod.isPresent()) {
            PaymentMethod u = paymentMethod.get();
            responseData = new ResponseData<>();
            responseData.setData(PaymentMethodMapper.INSTANCE.entityToDTO(u));
        } else {
            responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        }
        return responseData;
    }

    @Override
    public ResponseData<ResponsePage<PaymentMethodDTO>> findPaymentMethod(String paymentMethodName, Boolean isEnabled, Integer page, Integer pageSize) {
        ResponseData<ResponsePage<PaymentMethodDTO>> responseData = new ResponseData<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<PaymentMethod> paymentMethodPage = paymentMethodRepo.findPaymentMethod(paymentMethodName, isEnabled, pageable);

        List<PaymentMethodDTO> paymentMethodDTOS = paymentMethodPage.getContent().stream().map(PaymentMethodMapper.INSTANCE::entityToDTO).toList();

        ResponsePage<PaymentMethodDTO> responsePage = new ResponsePage<>(
                paymentMethodPage.getNumber() + 1,
                paymentMethodPage.getSize(),
                paymentMethodPage.getTotalElements(),
                paymentMethodPage.getTotalPages(),
                paymentMethodDTOS
        );
        responseData.setData(responsePage);
        return responseData;
    }
}
