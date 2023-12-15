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
@Table(name = "order_type")
public class OrderType extends Auditable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_type_name")
    private String orderTypeName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderType")
    private List<OrderDetail> orderDetails;
}
