package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.Unit;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UnitRepository extends JpaRepository<Unit, Integer> {

    @Query("SELECT u FROM Unit u WHERE LOWER(u.unitName) LIKE LOWER(CONCAT('%', ?1,'%'))")
    Page<Unit> findUnitByUnitName(String unitName, Pageable pageable);

//    @Modifying
//    @Query("update Unit u set u.unitName = :unitName where u.id = :id")
//    void updateUnit(@Param(value = "id") Integer id, @Param(value = "unitName") String unitName);

}
