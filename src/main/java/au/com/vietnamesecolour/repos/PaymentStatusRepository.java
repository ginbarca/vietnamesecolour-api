package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.PaymentStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Integer> {

    @Query("SELECT ps FROM PaymentStatus ps WHERE LOWER(ps.paymentStatusName) LIKE LOWER(CONCAT('%', ?1,'%'))")
    Page<PaymentStatus> findPaymentStatusByPaymentStatusName(String paymentStatusName, Pageable pageable);
}
