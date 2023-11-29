package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.TableBooking;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TableBookingRepository extends JpaRepository<TableBooking, Integer> {

    @Query("SELECT tb FROM TableBooking tb " +
            "WHERE LOWER(tb.customerName) LIKE LOWER(CONCAT('%', ?1,'%')) " +
            "AND LOWER(tb.mobileNumber) LIKE LOWER(CONCAT('%', ?2,'%')) " +
            "AND LOWER(tb.email) LIKE LOWER(CONCAT('%', ?3,'%')) " +
            "AND tb.bookingDate BETWEEN ?4 AND ?5")
    Page<TableBooking> findTableBooking(
            String customerName,
            String mobileNumber,
            String email,
            String bookingDateFrom,
            String bookingDateTo,
            Pageable pageable
    );
}
