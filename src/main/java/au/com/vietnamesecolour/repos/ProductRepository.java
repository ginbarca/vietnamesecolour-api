package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
