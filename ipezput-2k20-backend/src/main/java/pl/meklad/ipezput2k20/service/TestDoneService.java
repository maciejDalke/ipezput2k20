package pl.meklad.ipezput2k20.service;

import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.TestDoneDTO;

import java.util.Optional;

/**
 * Create by dev on 13.10.2020
 */
@Service
public interface TestDoneService {

    TestDoneDTO createTestDone(TestDoneDTO testDoneDTO);

    Iterable<TestDoneDTO> findAllTestDone();
    Optional<TestDoneDTO> findByTestDoneId(Long testDoneId);
    Optional<TestDoneDTO> findByTestId(Long groupId);

    TestDoneDTO updateTest(TestDoneDTO testDoneDTO, Long testDoneId);

    boolean deleteTestByTestId(Long testDoneId);
}
