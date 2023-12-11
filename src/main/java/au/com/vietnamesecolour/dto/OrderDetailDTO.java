package au.com.vietnamesecolour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    private Integer id;

    private Integer custId;

    private String custName;

    private String custEmail;

    private String custMobile;

    private String custAddress;

    private String note;

    private Float discount;

    private Float totalAmount;

    private String orderDate;

    private String orderTime;

    private Integer orderStatusId;

    private String orderStatusName;

    private Integer orderTypeId;

    private String orderTypeName;

    private List<DishInfoDTO> dishList;

    private Integer[] dishIds;

    private String createdDate;

    private String updatedDate;

    private String createdBy;

    private String updatedBy;
}
