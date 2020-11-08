package pl.meklad.ipezput2k20.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.QuestionDTO;
import pl.meklad.ipezput2k20.model.domain.Question;
import pl.meklad.ipezput2k20.repo.QuestionRepo;
import pl.meklad.ipezput2k20.service.QuestionService;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create by dev on 07.11.2020
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public QuestionServiceImpl(QuestionRepo questionRepo, ModelMapper modelMapper) {
        this.questionRepo = questionRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        Question question = convertToEntity(questionDTO);
        return convertToDto(question);
    }

    @Override
    public Iterable<QuestionDTO> findAllQuestions() {
        return questionRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionDTO> findQuestionByQuestionId(Long questionId) {
        return Optional.of(convertToDto(questionRepo.findById(questionId).orElseThrow()));
    }

    @Override
    public QuestionDTO updateQuestion(QuestionDTO questionDTO, Long questionId) {
        if (!questionRepo.existsById(questionId))
            return null;
        else {
            Question question = convertToEntity(questionDTO);
            question.setQuestionId(questionId);
            return convertToDto(question);
        }
    }

    @Override
    public boolean deleteQuestionByQuestionId(Long questionId) {
        if (!questionRepo.existsById(questionId))
            return false;
        else {
            questionRepo.deleteById(questionId);
            return true;
        }
    }

    //======================================================================================================================
    private Question convertToEntity(QuestionDTO questionDTO) {
        return modelMapper.map(questionDTO, Question.class);
    }

    private QuestionDTO convertToDto(Question question) {
        return modelMapper.map(question, QuestionDTO.class);
    }
}
