package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.Combo;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ComboRepository extends JpaRepository<Combo, Integer> {

    @Query("SELECT c FROM Combo c WHERE LOWER(c.comboName) LIKE LOWER(CONCAT('%', ?1,'%'))")
    Page<Combo> findComboByComboName(String comboName, Pageable pageable);
}
