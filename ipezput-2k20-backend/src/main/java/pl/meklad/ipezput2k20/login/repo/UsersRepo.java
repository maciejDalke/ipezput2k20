package pl.meklad.ipezput2k20.login.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.meklad.ipezput2k20.login.domain.Users;

import java.util.Optional;

/**
 * Create by dev on 24.10.2020
 */
@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByUserName (String username);
}
