package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.DishInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DishInfoRepository extends JpaRepository<DishInfo, Integer> {

    @Query("SELECT d FROM DishInfo d WHERE LOWER(d.dishName) LIKE LOWER(CONCAT('%', ?1,'%')) AND LOWER(d.dishGroup.dishGroupName) LIKE LOWER(CONCAT('%', ?2,'%'))")
    Page<DishInfo> findDishInfoByDishNameAndDishGroup(String dishName, String dishGroupName, Pageable pageable);
}
