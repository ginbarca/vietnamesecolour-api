package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.OrderType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface OrderTypeRepository extends JpaRepository<OrderType, Integer> {
}
