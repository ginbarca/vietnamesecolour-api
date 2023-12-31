package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    boolean existsUserByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
