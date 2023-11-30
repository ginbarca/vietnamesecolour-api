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
@Table(name = "combo")
public class Combo extends Auditable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "combo_name")
    private String comboName;

    @Column(name = "combo_price")
    private Float comboPrice;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "dish_combo",
            joinColumns = @JoinColumn(name = "combo_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<DishInfo> dishInfos;
}
