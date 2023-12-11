package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.dto.OrderDetailDTO;

import java.text.ParseException;

public interface OrderDetailService {

    ResponseData<OrderDetailDTO> createOrder(OrderDetailDTO payload) throws ParseException;
    ResponseData<OrderDetailDTO> updateOrder(Integer id, OrderDetailDTO payload) throws ParseException;
    ResponseData<Void> deleteOrderById(Integer id);
    ResponseData<OrderDetailDTO> getOrderById(Integer id);
    ResponseData<ResponsePage<OrderDetailDTO>> findOrder(String custName, String custEmail, String mobilePhone, Integer page, Integer pageSize);
}
