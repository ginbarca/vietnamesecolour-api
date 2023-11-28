package au.com.vietnamesecolour.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_group_id")
    private DishGroup dishGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;
}
