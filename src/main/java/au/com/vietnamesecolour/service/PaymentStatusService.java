package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.dto.PaymentStatusDTO;

public interface PaymentStatusService {

    ResponseData<PaymentStatusDTO> createPaymentStatus(PaymentStatusDTO payload);
    ResponseData<PaymentStatusDTO> updatePaymentStatus(Integer id, PaymentStatusDTO payload);
    ResponseData<Void> deletePaymentStatusById(Integer id);
    ResponseData<PaymentStatusDTO> getPaymentStatusById(Integer id);
    ResponseData<ResponsePage<PaymentStatusDTO>> findPaymentStatus(String paymentStatusName, Integer page, Integer pageSize);
}
