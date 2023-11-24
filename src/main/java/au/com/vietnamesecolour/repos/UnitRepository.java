package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.Unit;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UnitRepository extends JpaRepository<Unit, Integer> {

    @Query("SELECT u FROM Unit u WHERE LOWER(u.unitName) LIKE LOWER(CONCAT('%', ?1,'%'))")
    Page<Unit> findUnitByUnitName(String unitName, Pageable pageable);
}
