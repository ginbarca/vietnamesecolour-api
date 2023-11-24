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
@Table(name = "unit")
public class Unit extends Auditable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "unit_name")
    private String unitName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
    private List<DishInfo> dishInfos;
}
