package au.com.vietnamesecolour.mapper;

import au.com.vietnamesecolour.constant.DateConstant;
import au.com.vietnamesecolour.dto.OrderDetailDTO;
import au.com.vietnamesecolour.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDetailMapper {

    OrderDetailMapper INSTANCE = Mappers.getMapper( OrderDetailMapper.class );

    @Mapping(source = "createdDate", target = "createdDate", dateFormat = DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS)
    @Mapping(source = "updatedDate", target = "updatedDate", dateFormat = DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS)
    @Mapping(source = "orderDate", target = "orderDate", dateFormat = DateConstant.STR_PLAN_DD_MM_YYYY)
    @Mapping(source = "createdBy.username", target = "createdBy")
    @Mapping(source = "updatedBy.username", target = "updatedBy")
    @Mapping(source = "orderStatus.orderStatusName", target = "orderStatusName")
    @Mapping(source = "orderType.orderTypeName", target = "orderTypeName")
    OrderDetailDTO entityToDTO(OrderDetail entity);
}
