package au.com.vietnamesecolour.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail extends Auditable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id")
    private User customerInfo;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_email")
    private String custEmail;

    @Column(name = "cust_mobile")
    private String custMobile;

    @Column(name = "cust_address")
    private String custAddress;

    @Column(name = "note")
    private String note;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "subtotal")
    private Float subtotal;

    @Column(name = "total")
    private Float total;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_time")
    private String orderTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_type_id")
    private OrderType orderType;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "order_dish",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "dish_id")
//    )
//    private List<DishInfo> dishInfos;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderDetail")
    private List<OrderDish> orderDishes;
}
