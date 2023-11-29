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
@Table(name = "table_booking_status")
public class TableBookingStatus extends Auditable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "table_booking_status_name")
    private String bookingStatusName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingStatus")
    private List<TableBooking> tableBookings;
}
