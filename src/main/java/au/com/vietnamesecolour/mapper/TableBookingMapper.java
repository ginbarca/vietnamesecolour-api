package au.com.vietnamesecolour.mapper;

import au.com.vietnamesecolour.constant.DateConstant;
import au.com.vietnamesecolour.dto.TableBookingDTO;
import au.com.vietnamesecolour.entity.TableBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TableBookingMapper {

    TableBookingMapper INSTANCE = Mappers.getMapper( TableBookingMapper.class );

    @Mapping(source = "createdDate", target = "createdDate", dateFormat = DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS)
    @Mapping(source = "updatedDate", target = "updatedDate", dateFormat = DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS)
    @Mapping(source = "bookingDate", target = "bookingDate", dateFormat = DateConstant.STR_PLAN_DD_MM_YYYY)
    @Mapping(source = "createdBy.username", target = "createdBy")
    @Mapping(source = "updatedBy.username", target = "updatedBy")
    @Mapping(source = "bookingStatus.bookingStatusName", target = "bookingStatusName")
    TableBookingDTO entityToDTO(TableBooking entity);
}
