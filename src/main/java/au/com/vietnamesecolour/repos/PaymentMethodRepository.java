package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.PaymentMethod;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

    @Query("SELECT pm FROM PaymentMethod pm WHERE LOWER(pm.paymentMethodName) LIKE LOWER(CONCAT('%', ?1,'%')) AND (?2 is NULL or pm.enabled = ?2)")
    Page<PaymentMethod> findPaymentMethod(String paymentMethodName, Boolean isEnabled, Pageable pageable);
}
