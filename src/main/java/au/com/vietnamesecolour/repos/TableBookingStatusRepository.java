package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.DishGroup;
import au.com.vietnamesecolour.entity.TableBookingStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TableBookingStatusRepository extends JpaRepository<TableBookingStatus, Integer> {

    @Query("SELECT tbs FROM TableBookingStatus tbs WHERE LOWER(tbs.bookingStatusName) LIKE LOWER(CONCAT('%', ?1,'%'))")
    Page<TableBookingStatus> findTableBookingStatusByBookingStatusName(String bookingStatusName, Pageable pageable);
}
