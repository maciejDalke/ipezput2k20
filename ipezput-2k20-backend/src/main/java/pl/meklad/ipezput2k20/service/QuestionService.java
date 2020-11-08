package pl.meklad.ipezput2k20.service;

import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.QuestionDTO;

import java.util.Optional;

/**
 * Create by dev on 07.11.2020
 */
@Service
public interface QuestionService {

    QuestionDTO createQuestion(QuestionDTO questionDTO);

    Iterable<QuestionDTO> findAllQuestions();
    Optional<QuestionDTO> findQuestionByQuestionId(Long questionId);

    QuestionDTO updateQuestion(QuestionDTO questionDTO, Long questionId);

    boolean deleteQuestionByQuestionId(Long questionId);
}
