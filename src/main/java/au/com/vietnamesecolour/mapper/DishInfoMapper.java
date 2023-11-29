package au.com.vietnamesecolour.mapper;

import au.com.vietnamesecolour.constant.DateConstant;
import au.com.vietnamesecolour.dto.DishInfoDTO;
import au.com.vietnamesecolour.entity.DishInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DishInfoMapper {

    DishInfoMapper INSTANCE = Mappers.getMapper( DishInfoMapper.class );

    @Mapping(source = "createdDate", target = "createdDate", dateFormat = DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS)
    @Mapping(source = "updatedDate", target = "updatedDate", dateFormat = DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS)
    @Mapping(source = "createdBy.username", target = "createdBy")
    @Mapping(source = "updatedBy.username", target = "updatedBy")
    @Mapping(source = "dishGroup.dishGroupName", target = "dishGroupName")
    @Mapping(source = "unit.unitName", target = "unitName")
    DishInfoDTO entityToDTO(DishInfo entity);

}
