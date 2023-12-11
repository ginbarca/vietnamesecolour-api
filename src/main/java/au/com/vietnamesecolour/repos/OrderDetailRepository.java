package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.OrderDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query("SELECT od FROM OrderDetail od " +
            "WHERE LOWER(od.custName) LIKE LOWER(CONCAT('%', ?1,'%')) " +
            "AND LOWER(od.custEmail) LIKE LOWER(CONCAT('%', ?2,'%')) " +
            "AND LOWER(od.custMobile) LIKE LOWER(CONCAT('%', ?3,'%')) "
    )
    Page<OrderDetail> findOrderDetail(
            String custName,
            String custEmail,
            String mobilePhone,
            Pageable pageable
    );
}
