package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
