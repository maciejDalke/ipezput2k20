package pl.meklad.ipezput2k20.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.meklad.ipezput2k20.model.domain.Test;

import java.util.Optional;

/**
 * Create by dev on 24.10.2020
 */
@Repository
public interface TestRepo extends JpaRepository<Test, Long> {
    Optional<Test> findByGroupId(Long groupId);
}
