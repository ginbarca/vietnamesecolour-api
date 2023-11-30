package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.dto.PaymentMethodDTO;

public interface PaymentMethodService {

    ResponseData<PaymentMethodDTO> createPaymentMethod(PaymentMethodDTO payload);
    ResponseData<PaymentMethodDTO> updatePaymentMethod(Integer id, PaymentMethodDTO payload);
    ResponseData<Void> deletePaymentMethodById(Integer id);
    ResponseData<PaymentMethodDTO> getPaymentMethodById(Integer id);
    ResponseData<ResponsePage<PaymentMethodDTO>> findPaymentMethod(String paymentMethodName, Boolean isEnabled, Integer page, Integer pageSize);
}
