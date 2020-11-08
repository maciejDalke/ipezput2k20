package pl.meklad.ipezput2k20.service;

import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.TestQuestionDTO;

import java.util.Optional;

/**
 * Create by dev on 13.10.2020
 */
@Service
public interface TestQuestionService {

    TestQuestionDTO createTestQuestion(TestQuestionDTO testQuestionDTO);

    Iterable<TestQuestionDTO> findAllTestQuestion();
    //    TPage<TestQuestionDTO> getAllPageable(Pageable pageable) throws NotFoundException;
    Optional<TestQuestionDTO> findByTestQuestionId(Long testQuestionId);
    Optional<TestQuestionDTO> findTestQuestionByTestId(Long testQuestionId);

    TestQuestionDTO updateTestQuestion(TestQuestionDTO testQuestionDTO, Long testQuestionId);

    boolean deleteTestQuestionByTestQuestionId(Long testQuestionId);
}
