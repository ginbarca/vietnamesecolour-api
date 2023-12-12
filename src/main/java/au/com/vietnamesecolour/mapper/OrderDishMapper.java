package au.com.vietnamesecolour.mapper;

import au.com.vietnamesecolour.dto.OrderDishDTO;
import au.com.vietnamesecolour.entity.OrderDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDishMapper {

    OrderDishMapper INSTANCE = Mappers.getMapper( OrderDishMapper.class );

    @Mapping(source = "orderDetail.id", target = "orderId")
    @Mapping(source = "dishInfo.id", target = "dishId")
    @Mapping(source = "dishInfo.dishName", target = "dishName")
    @Mapping(source = "dishInfo.price", target = "price")
    @Mapping(source = "dishInfo.dishImageName", target = "dishImageName")
    @Mapping(source = "dishInfo.unit.unitName", target = "unitName")
    OrderDishDTO entityToDTO(OrderDish entity);
}
