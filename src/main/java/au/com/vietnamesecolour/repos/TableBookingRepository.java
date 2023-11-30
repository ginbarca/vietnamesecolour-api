package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.TableBooking;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Transactional
public interface TableBookingRepository extends JpaRepository<TableBooking, Integer> {

    @Query("SELECT tb FROM TableBooking tb " +
            "WHERE LOWER(tb.customerName) LIKE LOWER(CONCAT('%', COALESCE(?1, '') ,'%')) " +
            "AND LOWER(tb.mobileNumber) LIKE LOWER(CONCAT('%', COALESCE(?2, ''),'%')) " +
            "AND LOWER(tb.email) LIKE LOWER(CONCAT('%', COALESCE(?3, ''),'%')) " +
            "AND (tb.bookingDate >= ?4 OR ?4 IS NULL) " +
            "AND (tb.bookingDate <= ?5 OR ?5 IS NULL)")
    Page<TableBooking> findTableBooking(
            String customerName,
            String mobileNumber,
            String email,
            Date bookingDateFrom,
            Date bookingDateTo,
            Pageable pageable
    );
}
