package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.DishGroup;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DishGroupRepository extends JpaRepository<DishGroup, Integer> {

    @Query("SELECT dg FROM DishGroup dg WHERE LOWER(dg.dishGroupName) LIKE LOWER(CONCAT('%', ?1,'%'))")
    Page<DishGroup> findDishGroupByDishGroupName(String dishGroupName, Pageable pageable);
}
