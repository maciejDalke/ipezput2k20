package pl.meklad.ipezput2k20.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.meklad.ipezput2k20.model.domain.Grade;

import java.util.List;

/**
 * Create by dev on 07.11.2020
 */
@Repository
public interface GradeRepo extends JpaRepository<Grade,Long> {
    List<Grade> findAllByUserId (Long userId);
    //    @Query("select u from User u where u.username like %:username%")
//    List<Group> getByUsername(@Param("username") String username);
}
