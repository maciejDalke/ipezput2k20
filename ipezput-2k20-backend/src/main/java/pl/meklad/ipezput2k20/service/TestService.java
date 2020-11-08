package pl.meklad.ipezput2k20.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.TestDTO;

import java.util.Optional;

/**
 * Create by dev on 13.10.2020
 */
@Service
public interface TestService {

    TestDTO createTest(TestDTO testDTO);

    Iterable<TestDTO> findAllTest();
//    TPage<TestDTO> getAllPageable(Pageable pageable) throws NotFoundException;
    Optional<TestDTO> findByTestId (Long testId);
    Optional<TestDTO> findByGroupId (Long groupId);

    TestDTO updateTest(TestDTO testDTO, Long testId) throws NotFoundException;

    boolean deleteTestByTestId(Long testId);
}
