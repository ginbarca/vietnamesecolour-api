package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.dto.InvoiceDTO;
import au.com.vietnamesecolour.entity.Invoice;
import au.com.vietnamesecolour.entity.OrderDetail;
import au.com.vietnamesecolour.entity.PaymentMethod;
import au.com.vietnamesecolour.entity.PaymentStatus;
import au.com.vietnamesecolour.mapper.InvoiceMapper;
import au.com.vietnamesecolour.repos.InvoiceRepository;
import au.com.vietnamesecolour.repos.OrderDetailRepository;
import au.com.vietnamesecolour.repos.PaymentMethodRepository;
import au.com.vietnamesecolour.repos.PaymentStatusRepository;
import au.com.vietnamesecolour.service.InvoiceService;
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
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepo;
    private final OrderDetailRepository orderDetailRepo;
    private final PaymentMethodRepository paymentMethodRepo;
    private final PaymentStatusRepository paymentStatusRepo;

    @Override
    public ResponseData<InvoiceDTO> createInvoice(InvoiceDTO payload) {
        ResponseData<InvoiceDTO> responseData;
        if (!orderDetailRepo.existsById(payload.getOrderId())){
            responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no order with ID " + payload.getOrderId());
            return responseData;
        }
        if (!paymentMethodRepo.existsById(payload.getPmMethodId())){
            responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no payment method with ID " + payload.getPmMethodId());
            return responseData;
        }
        if (!paymentStatusRepo.existsById(payload.getPmStatusId())){
            responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no payment status with ID " + payload.getPmStatusId());
            return responseData;
        }
        OrderDetail orderDetail = orderDetailRepo.getReferenceById(payload.getOrderId());
        PaymentMethod paymentMethod = paymentMethodRepo.getReferenceById(payload.getPmMethodId());
        PaymentStatus paymentStatus = paymentStatusRepo.getReferenceById(payload.getPmStatusId());
        Invoice invoice = Invoice
                .builder()
                .orderDetail(orderDetail)
                .paymentMethod(paymentMethod)
                .paymentStatus(paymentStatus)
                .build();
        Invoice savedInvoice = invoiceRepo.save(invoice);
        InvoiceDTO dto = InvoiceMapper.INSTANCE.entityToDTO(savedInvoice);
        responseData = new ResponseData<>(ResponseStatusCode.CREATED.getCode(), ResponseStatusCode.CREATED.getDescription());
        responseData.setData(dto);
        return responseData;
    }

    @Override
    public ResponseData<InvoiceDTO> updateInvoice(Integer id, InvoiceDTO payload) {
        Optional<Invoice> optInvoice = invoiceRepo.findById(id);
        ResponseData<InvoiceDTO> responseData;
        if (optInvoice.isPresent()) {
            if (!orderDetailRepo.existsById(payload.getOrderId())){
                responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no order with ID " + payload.getOrderId());
                return responseData;
            }
            if (!paymentMethodRepo.existsById(payload.getPmMethodId())){
                responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no payment method with ID " + payload.getPmMethodId());
                return responseData;
            }
            if (!paymentStatusRepo.existsById(payload.getPmStatusId())){
                responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no payment status with ID " + payload.getPmStatusId());
                return responseData;
            }
            OrderDetail orderDetail = orderDetailRepo.getReferenceById(payload.getOrderId());
            PaymentMethod paymentMethod = paymentMethodRepo.getReferenceById(payload.getPmMethodId());
            PaymentStatus paymentStatus = paymentStatusRepo.getReferenceById(payload.getPmStatusId());
            Invoice invoice = optInvoice.get();
            invoice.setOrderDetail(orderDetail);
            invoice.setPaymentMethod(paymentMethod);
            invoice.setPaymentStatus(paymentStatus);
            Invoice savedInvoice = invoiceRepo.save(invoice);
            InvoiceDTO dto = InvoiceMapper.INSTANCE.entityToDTO(savedInvoice);
            
            responseData = new ResponseData<>();
            responseData.setData(dto);
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<Void> deleteInvoiceById(Integer id) {
        ResponseData<Void> responseData;
        if (invoiceRepo.existsById(id)) {
            invoiceRepo.deleteById(id);
            responseData = new ResponseData<>(ResponseStatusCode.OK.getCode(), ResponseStatusCode.OK.getDescription());
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<InvoiceDTO> getInvoiceById(Integer id) {
        ResponseData<InvoiceDTO> responseData;
        Optional<Invoice> optInvoice = invoiceRepo.findById(id);
        if (optInvoice.isPresent()) {
            Invoice invoice = optInvoice.get();
            responseData = new ResponseData<>();
            responseData.setData(InvoiceMapper.INSTANCE.entityToDTO(invoice));
        } else {
            responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        }
        return responseData;
    }

    @Override
    public ResponseData<ResponsePage<InvoiceDTO>> findInvoice(String invoiceCode, Integer page, Integer pageSize) {
        ResponseData<ResponsePage<InvoiceDTO>> responseData = new ResponseData<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Invoice> invoicePage = invoiceRepo.findByInvoiceCode(invoiceCode, pageable);

        List<InvoiceDTO> invoiceDTOS = invoicePage.getContent().stream().map(InvoiceMapper.INSTANCE::entityToDTO).toList();

        ResponsePage<InvoiceDTO> responsePage = new ResponsePage<>(
                invoicePage.getNumber() + 1,
                invoicePage.getSize(),
                invoicePage.getTotalElements(),
                invoicePage.getTotalPages(),
                invoiceDTOS
        );
        responseData.setData(responsePage);
        return responseData;
    }
}
