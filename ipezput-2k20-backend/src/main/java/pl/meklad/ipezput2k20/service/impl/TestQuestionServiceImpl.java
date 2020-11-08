package pl.meklad.ipezput2k20.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.TestQuestionDTO;
import pl.meklad.ipezput2k20.model.domain.TestQuestions;
import pl.meklad.ipezput2k20.repo.TestQuestionRepo;
import pl.meklad.ipezput2k20.service.TestQuestionService;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create by dev on 07.11.2020
 */
@Service
public class TestQuestionServiceImpl implements TestQuestionService {

    private final TestQuestionRepo testQuestionRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public TestQuestionServiceImpl(TestQuestionRepo testQuestionRepo, ModelMapper modelMapper) {
        this.testQuestionRepo = testQuestionRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public TestQuestionDTO createTestQuestion(TestQuestionDTO testQuestionDTO) {
        TestQuestions testQuestions = convertToEntity(testQuestionDTO);
        return convertToDto(testQuestions);
    }

    @Override
    public Iterable<TestQuestionDTO> findAllTestQuestion() {
        return testQuestionRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<TestQuestionDTO> findByTestQuestionId(Long testQuestionId) {
        return Optional.of(convertToDto(testQuestionRepo.findById(testQuestionId).orElseThrow()));
    }

    @Override
    public Optional<TestQuestionDTO> findTestQuestionByTestId(Long testId) {
        return Optional.of(convertToDto(testQuestionRepo.findByTestId(testId).orElseThrow()));
    }

    @Override
    public TestQuestionDTO updateTestQuestion(TestQuestionDTO testQuestionDTO, Long testQuestionId) {
        if (!testQuestionRepo.existsById(testQuestionId))
            return null;
        else {
            TestQuestions testQuestions = convertToEntity(testQuestionDTO);
            testQuestions.setQuestion4TestId(testQuestionId);
            return convertToDto(testQuestions);
        }
    }

    @Override
    public boolean deleteTestQuestionByTestQuestionId(Long testQuestionId) {
        if (!testQuestionRepo.existsById(testQuestionId))
            return false;
        else {
            testQuestionRepo.deleteById(testQuestionId);
            return true;
        }
    }

    //======================================================================================================================
    private TestQuestions convertToEntity(TestQuestionDTO testQuestionDTO) {
        return modelMapper.map(testQuestionDTO, TestQuestions.class);
    }

    private TestQuestionDTO convertToDto(TestQuestions testQuestions) {
        return modelMapper.map(testQuestions, TestQuestionDTO.class);
    }
}
