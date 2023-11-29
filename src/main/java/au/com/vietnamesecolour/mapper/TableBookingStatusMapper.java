package au.com.vietnamesecolour.mapper;

import au.com.vietnamesecolour.constant.DateConstant;
import au.com.vietnamesecolour.dto.DishGroupDTO;
import au.com.vietnamesecolour.dto.TableBookingStatusDTO;
import au.com.vietnamesecolour.entity.DishGroup;
import au.com.vietnamesecolour.entity.TableBookingStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TableBookingStatusMapper {

    TableBookingStatusMapper INSTANCE = Mappers.getMapper( TableBookingStatusMapper.class );

    @Mapping(source = "createdDate", target = "createdDate", dateFormat = DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS)
    @Mapping(source = "updatedDate", target = "updatedDate", dateFormat = DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS)
    @Mapping(source = "createdBy.username", target = "createdBy")
    @Mapping(source = "updatedBy.username", target = "updatedBy")
    TableBookingStatusDTO entityToDTO(TableBookingStatus entity);

}
