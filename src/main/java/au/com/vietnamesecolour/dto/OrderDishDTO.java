package au.com.vietnamesecolour.dto;

import au.com.vietnamesecolour.config.data.ViewMode;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishDTO {

    @JsonView({ViewMode.Private.class})
    private Integer orderId;

    @JsonView({ViewMode.Private.class})
    private Integer dishId;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String dishName;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String dishImageName;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private Integer quantity;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private Float price;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String unitName;

    @JsonView({ViewMode.Private.class, ViewMode.Public.class})
    private String note;
}
