package pl.meklad.ipezput2k20.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.TestDoneDTO;
import pl.meklad.ipezput2k20.model.domain.TestDone;
import pl.meklad.ipezput2k20.repo.TestDoneRepo;
import pl.meklad.ipezput2k20.service.TestDoneService;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create by dev on 07.11.2020
 */
@Service
public class TestDoneServiceImpl implements TestDoneService {

    private final TestDoneRepo testDoneRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public TestDoneServiceImpl(TestDoneRepo testDoneRepo, ModelMapper modelMapper) {
        this.testDoneRepo = testDoneRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public TestDoneDTO createTestDone(TestDoneDTO testDoneDTO) {
        TestDone testDone = convertToEntity(testDoneDTO);
        return convertToDto(testDone);
    }

    @Override
    public Iterable<TestDoneDTO> findAllTestDone() {
        return testDoneRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<TestDoneDTO> findByTestDoneId(Long testDoneId) {
        return Optional.of(convertToDto(testDoneRepo.findById(testDoneId).orElseThrow()));
    }

    @Override
    public Optional<TestDoneDTO> findByTestId(Long testId) {
        return Optional.of(convertToDto(testDoneRepo.findByTestId(testId).orElseThrow()));
    }

    @Override
    public TestDoneDTO updateTest(TestDoneDTO testDoneDTO, Long testDoneId) {
        if (!testDoneRepo.existsById(testDoneId))
            return null;
        else {
            TestDone testDone = convertToEntity(testDoneDTO);
            testDone.setTestDoneId(testDoneId);
            return convertToDto(testDone);
        }
    }

    @Override
    public boolean deleteTestByTestId(Long testDoneId) {
        if (!testDoneRepo.existsById(testDoneId))
            return false;
        else {
            testDoneRepo.deleteById(testDoneId);
            return true;
        }
    }

    //======================================================================================================================
    private TestDone convertToEntity(TestDoneDTO testDoneDTO) {
        return modelMapper.map(testDoneDTO, TestDone.class);
    }

    private TestDoneDTO convertToDto(TestDone testDone) {
        return modelMapper.map(testDone, TestDoneDTO.class);
    }
}
