package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findById(Integer id);
}
