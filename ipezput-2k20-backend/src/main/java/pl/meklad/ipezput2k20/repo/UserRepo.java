package pl.meklad.ipezput2k20.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.meklad.ipezput2k20.model.domain.User;

import java.util.List;

/**
 * Create by dev on 24.10.2020
 */
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername (String username);

    @Query("select u from User u where u.username like %:username%")
    List<User> getByUsername(@Param("username") String username);

    List<User> findByEmail (String email);
}
