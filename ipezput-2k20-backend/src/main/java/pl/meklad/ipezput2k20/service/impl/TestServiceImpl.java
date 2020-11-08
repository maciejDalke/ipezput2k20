package pl.meklad.ipezput2k20.service.impl;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.TestDTO;
import pl.meklad.ipezput2k20.model.domain.Test;
import pl.meklad.ipezput2k20.repo.TestRepo;
import pl.meklad.ipezput2k20.service.TestService;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create by dev on 06.11.2020
 */
@Service
public class TestServiceImpl implements TestService {

    private final ModelMapper modelMapper;
    private final TestRepo testRepo;

    @Autowired
    public TestServiceImpl(ModelMapper modelMapper, TestRepo testRepo) {
        this.modelMapper = modelMapper;
        this.testRepo = testRepo;
    }

    @Override
    public TestDTO createTest(TestDTO testDTO) {
        Test test = convertToEntity(testDTO);
        return convertToDto(test);
    }

    @Override
    public Iterable<TestDTO> findAllTest() {
        return testRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

//    @Override
//    public TPage<TestDTO> getAllPageable(Pageable pageable) throws NotFoundException {
//        return null;
//    }

    @Override
    public Optional<TestDTO> findByTestId(Long testId) {
        return Optional.of(convertToDto(testRepo.findById(testId).orElseThrow(IllegalArgumentException::new)));
    }

    @Override
    public Optional<TestDTO> findByGroupId(Long groupId) {
        return Optional.of(convertToDto(testRepo.findByGroupId(groupId).orElseThrow(IllegalArgumentException::new)));
    }

    @Override
    public TestDTO updateTest(TestDTO testDTO, Long testId) throws NotFoundException {
        if (!testRepo.existsById(testId))
            return null;
        else {
            Test test = convertToEntity(testDTO);
            test.setTestId(testId);
            return convertToDto(test);
        }
    }

    @Override
    public boolean deleteTestByTestId(Long testId) {
        if (!testRepo.existsById(testId))
            return false;
        else {
            testRepo.deleteById(testId);
            return true;
        }
    }

    //======================================================================================================================
    private Test convertToEntity(TestDTO testDTO) {
        return modelMapper.map(testDTO, Test.class);
    }

    private TestDTO convertToDto(Test test) {
        return modelMapper.map(test, TestDTO.class);
    }
}
