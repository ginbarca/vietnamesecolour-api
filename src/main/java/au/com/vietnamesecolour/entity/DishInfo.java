package au.com.vietnamesecolour.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dish")
public class DishInfo extends Auditable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dish_name")
    private String dishName;

    @Column(name = "price")
    private Float price;

    @Column(name = "dish_description")
    private String dishDescription;

    @Column(name = "dish_image_path")
    private String dishImagePath;

    @Column(name = "dish_image_name")
    private String dishImageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_group_id")
    private DishGroup dishGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToMany(mappedBy = "dishInfos")
    private List<Combo> comboList;

//    @ManyToMany(mappedBy = "dishInfos")
//    private List<OrderDetail> orderDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dishInfo")
    private List<OrderDish> orderDishes;
}
