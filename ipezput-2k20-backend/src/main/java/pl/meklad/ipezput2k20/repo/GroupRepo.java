package pl.meklad.ipezput2k20.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.meklad.ipezput2k20.model.domain.Group;

/**
 * Create by dev on 24.10.2020
 */
@Repository
public interface GroupRepo extends JpaRepository<Group,Long> {
    Iterable<Group> findAllByName (String name);

//    @Query("select u from User u where u.username like %:username%")
//    List<Group> getByUsername(@Param("username") String username);

}
