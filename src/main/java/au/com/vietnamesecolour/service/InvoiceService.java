package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.dto.InvoiceDTO;

public interface InvoiceService {

    ResponseData<InvoiceDTO> createInvoice(InvoiceDTO payload);

    ResponseData<InvoiceDTO> updateInvoice(Integer id, InvoiceDTO payload);

    ResponseData<Void> deleteInvoiceById(Integer id);

    ResponseData<InvoiceDTO> getInvoiceById(Integer id);

    ResponseData<ResponsePage<InvoiceDTO>> findInvoice(String invoiceCode, Integer page, Integer pageSize);
    
}
