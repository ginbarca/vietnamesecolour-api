package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponsePage;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.constant.DateConstant;
import au.com.vietnamesecolour.dto.OrderDetailDTO;
import au.com.vietnamesecolour.dto.OrderDishDTO;
import au.com.vietnamesecolour.entity.DishInfo;
import au.com.vietnamesecolour.entity.OrderDetail;
import au.com.vietnamesecolour.entity.OrderDish;
import au.com.vietnamesecolour.mapper.OrderDetailMapper;
import au.com.vietnamesecolour.repos.*;
import au.com.vietnamesecolour.service.OrderService;
import jakarta.transaction.Transactional;
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
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDetailRepository orderRepo;
    private final UserRepository userRepo;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderTypeRepository orderTypeRepository;
    private final DishInfoRepository dishInfoRepository;
    private final OrderDishRepository orderDishRepository;

    @Override
    public ResponseData<OrderDetailDTO> createOrder(OrderDetailDTO payload) throws ParseException {
        ResponseData<OrderDetailDTO> responseData;
        Float totalPrice = 0f;
        List<OrderDish> orderDishes = new ArrayList<>();
        for (OrderDishDTO ordDish : payload.getOrderDishes()) {
            Optional<DishInfo> optDishInfo = dishInfoRepository.findById(ordDish.getDishId());
            if (optDishInfo.isEmpty()) {
                responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no dish with ID " + ordDish.getDishId());
                return responseData;
            }
            DishInfo dishInfo = optDishInfo.get();
            totalPrice += dishInfo.getPrice();
            orderDishes.add(
                    OrderDish
                            .builder()
                            .dishInfo(dishInfo)
                            .quantity(ordDish.getQuantity())
                            .price(dishInfo.getPrice())
                            .note(ordDish.getNote())
                            .build()
            );
        }
        OrderDetail orderDetail = OrderDetail
                .builder()
                .customerInfo(Objects.nonNull(payload.getCustId()) ? userRepo.findById(payload.getCustId()).get() : null)
                .custName(payload.getCustName())
                .custEmail(payload.getCustEmail())
                .custMobile(payload.getCustMobile())
                .custAddress(payload.getCustAddress())
                .note(payload.getNote())
                .discount(payload.getDiscount())
                .totalAmount(totalPrice)
                .orderDate(DateUtils.parseDate(payload.getOrderDate(), DateConstant.STR_PLAN_DD_MM_YYYY))
                .orderTime(payload.getOrderTime())
                .orderStatus(orderStatusRepository.findById(payload.getOrderStatusId()).get())
                .orderType(orderTypeRepository.findById(payload.getOrderTypeId()).get())
                .build();
        OrderDetail savedOrderDetail = orderRepo.save(orderDetail);
        orderDishes.forEach(item -> item.setOrderDetail(savedOrderDetail));
        orderDishRepository.saveAll(orderDishes);

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
            Float totalPrice = 0f;
            List<OrderDish> orderDishes = new ArrayList<>();
            for (OrderDishDTO ordDish : payload.getOrderDishes()) {
                Optional<DishInfo> optDishInfo = dishInfoRepository.findById(ordDish.getDishId());
                if (optDishInfo.isEmpty()) {
                    responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no dish with ID " + ordDish.getDishId());
                    return responseData;
                }
                DishInfo dishInfo = optDishInfo.get();
                totalPrice += dishInfo.getPrice();
                orderDishes.add(
                        OrderDish
                                .builder()
                                .dishInfo(dishInfo)
                                .quantity(ordDish.getQuantity())
                                .price(dishInfo.getPrice())
                                .note(ordDish.getNote())
                                .build()
                );
            }
            OrderDetail ord = orderSearch.get();
            ord.setCustomerInfo(Objects.nonNull(payload.getCustId()) ? userRepo.findById(payload.getCustId()).get() : null);
            ord.setCustName(payload.getCustName());
            ord.setCustEmail(payload.getCustEmail());
            ord.setCustMobile(payload.getCustMobile());
            ord.setCustAddress(payload.getCustAddress());
            ord.setNote(payload.getNote());
            ord.setDiscount(payload.getDiscount());
            ord.setTotalAmount(totalPrice);
            ord.setOrderDate(DateUtils.parseDate(payload.getOrderDate(), DateConstant.STR_PLAN_DD_MM_YYYY));
            ord.setOrderTime(payload.getOrderTime());
            ord.setOrderStatus(orderStatusRepository.findById(payload.getOrderStatusId()).get());
            ord.setOrderType(orderTypeRepository.findById(payload.getOrderTypeId()).get());

            OrderDetail savedOrderDetail = orderRepo.save(ord);
            orderDishes.forEach(item -> item.setOrderDetail(savedOrderDetail));
            orderDishRepository.saveAll(orderDishes);
            OrderDetailDTO dto = OrderDetailMapper.INSTANCE.entityToDTO(savedOrderDetail);
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
