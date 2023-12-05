package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.DishGroup;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface DishGroupRepository extends JpaRepository<DishGroup, Integer> {

    @Query("SELECT dg FROM DishGroup dg WHERE LOWER(dg.dishGroupName) LIKE LOWER(CONCAT('%', ?1,'%'))")
    Page<DishGroup> findDishGroupByDishGroupName(String dishGroupName, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = { "dishInfos", "dishInfos.unit" })
//    @Query("SELECT dg FROM DishGroup dg LEFT JOIN FETCH dg.dishInfos")
    List<DishGroup> findAll();
}
