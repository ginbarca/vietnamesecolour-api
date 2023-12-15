package au.com.vietnamesecolour.mapper;

import au.com.vietnamesecolour.constant.DateConstant;
import au.com.vietnamesecolour.dto.InvoiceDTO;
import au.com.vietnamesecolour.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { DishInfoMapper.class, OrderDishMapper.class })
public interface InvoiceMapper {

    InvoiceMapper INSTANCE = Mappers.getMapper( InvoiceMapper.class );

    @Mapping(source = "createdDate", target = "createdDate", dateFormat = DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS)
    @Mapping(source = "updatedDate", target = "updatedDate", dateFormat = DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS)
    @Mapping(source = "createdBy.username", target = "createdBy")
    @Mapping(source = "updatedBy.username", target = "updatedBy")
    @Mapping(source = "paymentStatus.id", target = "pmStatusId")
    @Mapping(source = "paymentStatus.paymentStatusName", target = "pmStatusName")
    @Mapping(source = "paymentMethod.id", target = "pmMethodId")
    @Mapping(source = "paymentMethod.paymentMethodName", target = "pmMethodName")
    @Mapping(source = "orderDetail.orderDishes", target = "orderDishes")
    @Mapping(source = "orderDetail.note", target = "note")
    @Mapping(source = "orderDetail.discount", target = "discount")
    @Mapping(source = "orderDetail.subtotal", target = "subtotal")
    @Mapping(source = "orderDetail.total", target = "total")
    @Mapping(source = "orderDetail.id", target = "orderId")
    InvoiceDTO entityToDTO(Invoice entity);

}
