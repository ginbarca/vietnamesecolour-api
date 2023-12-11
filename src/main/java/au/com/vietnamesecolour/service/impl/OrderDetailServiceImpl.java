package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.constant.DateConstant;
import au.com.vietnamesecolour.dto.OrderDetailDTO;
import au.com.vietnamesecolour.entity.DishInfo;
import au.com.vietnamesecolour.entity.OrderDetail;
import au.com.vietnamesecolour.mapper.OrderDetailMapper;
import au.com.vietnamesecolour.repos.*;
import au.com.vietnamesecolour.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderRepo;
    private final UserRepository userRepo;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderTypeRepository orderTypeRepository;
    private final DishInfoRepository dishInfoRepository;

    @Override
    public ResponseData<OrderDetailDTO> createOrder(OrderDetailDTO payload) throws ParseException {
        ResponseData<OrderDetailDTO> responseData;
        OrderDetail orderDetail = OrderDetail
                .builder()
                .customerInfo(Objects.nonNull(payload.getCustId()) ? userRepo.findById(payload.getCustId()).get() : null)
                .custName(payload.getCustName())
                .custEmail(payload.getCustEmail())
                .custMobile(payload.getCustMobile())
                .custAddress(payload.getCustAddress())
                .note(payload.getNote())
                .discount(payload.getDiscount())
                .totalAmount(payload.getTotalAmount())
                .orderDate(DateUtils.parseDate(payload.getOrderDate(), DateConstant.STR_PLAN_DD_MM_YYYY))
                .orderTime(payload.getOrderTime())
                .orderStatus(orderStatusRepository.findById(payload.getOrderStatusId()).get())
                .orderType(orderTypeRepository.findById(payload.getOrderTypeId()).get())
                .build();
        List<DishInfo> dishInfos = new ArrayList<>();
        for (Integer id : payload.getDishIds()) {
            Optional<DishInfo> dishInfo = dishInfoRepository.findById(id);
            if (dishInfo.isEmpty()) {
                responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no dish with ID " + id);
                return responseData;
            }
            dishInfos.add(dishInfo.get());
        }
        orderDetail.setDishInfos(dishInfos);
        OrderDetail savedOrderDetail = orderRepo.save(orderDetail);
        OrderDetailDTO dto = OrderDetailMapper.INSTANCE.entityToDTO(savedOrderDetail);
        responseData = new ResponseData<>(ResponseStatusCode.CREATED.getCode(), ResponseStatusCode.CREATED.getDescription());
        responseData.setData(dto);
        return responseData;
    }

    @Override
    public ResponseData<OrderDetailDTO> updateOrder(Integer id, OrderDetailDTO payload) throws ParseException {
        Optional<OrderDetail> orderSearch = orderRepo.findById(id);
        ResponseData<OrderDetailDTO> responseData;
        if (orderSearch.isPresent()) {
            OrderDetail ord = orderSearch.get();
            ord.setCustomerInfo(Objects.nonNull(payload.getCustId()) ? userRepo.findById(payload.getCustId()).get() : null);
            ord.setCustName(payload.getCustName());
            ord.setCustEmail(payload.getCustEmail());
            ord.setCustMobile(payload.getCustMobile());
            ord.setCustAddress(payload.getCustAddress());
            ord.setNote(payload.getNote());
            ord.setDiscount(payload.getDiscount());
            ord.setTotalAmount(payload.getTotalAmount());
            ord.setOrderDate(DateUtils.parseDate(payload.getOrderDate(), DateConstant.STR_PLAN_DD_MM_YYYY));
            ord.setOrderTime(payload.getOrderTime());
            ord.setOrderStatus(orderStatusRepository.findById(payload.getOrderStatusId()).get());
            ord.setOrderType(orderTypeRepository.findById(payload.getOrderTypeId()).get());
            List<DishInfo> dishInfos = new ArrayList<>();
            for (Integer dishId : payload.getDishIds()) {
                Optional<DishInfo> dishInfo = dishInfoRepository.findById(dishId);
                if (dishInfo.isEmpty()) {
                    responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no dish with ID " + id);
                    return responseData;
                }
                dishInfos.add(dishInfo.get());
            }
            ord.setDishInfos(dishInfos);

            OrderDetail savedBooking = orderRepo.save(ord);
            OrderDetailDTO dto = OrderDetailMapper.INSTANCE.entityToDTO(savedBooking);
            responseData = new ResponseData<>();
            responseData.setData(dto);
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<Void> deleteOrderById(Integer id) {
        boolean isOrderExist = orderRepo.existsById(id);
        ResponseData<Void> responseData;
        if (isOrderExist) {
            orderRepo.deleteById(id);
            responseData = new ResponseData<>(ResponseStatusCode.OK.getCode(), ResponseStatusCode.OK.getDescription());
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<OrderDetailDTO> getOrderById(Integer id) {
        Optional<OrderDetail> orderSearch = orderRepo.findById(id);
        ResponseData<OrderDetailDTO> responseData;
        if (orderSearch.isPresent()) {
            responseData = new ResponseData<>();
            responseData.setData(OrderDetailMapper.INSTANCE.entityToDTO(orderSearch.get()));
            return responseData;
        }
        responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.DATA_NOT_FOUND.getMessage());
        return responseData;
    }

    @Override
    public ResponseData<ResponsePage<OrderDetailDTO>> findOrder(String custName, String custEmail, String mobilePhone, Integer page, Integer pageSize) {
        ResponseData<ResponsePage<OrderDetailDTO>> responseData = new ResponseData<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<OrderDetail> orderpage = orderRepo.findOrderDetail(custName, custEmail, mobilePhone, pageable);

        List<OrderDetailDTO> bookingDTOS = orderpage.getContent().stream().map(OrderDetailMapper.INSTANCE::entityToDTO).toList();

        ResponsePage<OrderDetailDTO> responsePage = new ResponsePage<>(
                orderpage.getNumber() + 1,
                orderpage.getSize(),
                orderpage.getTotalElements(),
                orderpage.getTotalPages(),
                bookingDTOS
        );
        responseData.setData(responsePage);
        return responseData;
    }
}
