package pl.meklad.ipezput2k20.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.GradeDTO;
import pl.meklad.ipezput2k20.model.domain.Grade;
import pl.meklad.ipezput2k20.repo.GradeRepo;
import pl.meklad.ipezput2k20.service.GradeService;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create by dev on 07.11.2020
 */
@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepo gradeRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public GradeServiceImpl(GradeRepo gradeRepo, ModelMapper modelMapper) {
        this.gradeRepo = gradeRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public GradeDTO createGrade(GradeDTO gradeDTO) {
        Grade grade = convertToEntity(gradeDTO);
        return convertToDto(grade);
    }

    @Override
    public Iterable<GradeDTO> findAllGrades() {
        return gradeRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<GradeDTO> findByGradeId(Long gradeId) {
        return Optional.of(convertToDto(gradeRepo.findById(gradeId).orElseThrow()));
    }

    @Override
    public Optional<GradeDTO> findByUserId(Long userId) {
        return Optional.of(convertToDto((Grade) gradeRepo.findAllByUserId(userId)));
    }

    @Override
    public GradeDTO updateGrade(GradeDTO gradeDTO, Long gradeId) {
        if (!gradeRepo.existsById(gradeId))
            return null;
        else {
            Grade grade = convertToEntity(gradeDTO);
            grade.setGradeId(gradeId);
            return convertToDto(grade);
        }
    }

    @Override
    public boolean deleteTestByTestId(Long gradeId) {
        if (!gradeRepo.existsById(gradeId))
            return false;
        else {
            gradeRepo.deleteById(gradeId);
            return true;
        }
    }

    //======================================================================================================================
    private Grade convertToEntity(GradeDTO gradeDTO) {
        return modelMapper.map(gradeDTO, Grade.class);
    }

    private GradeDTO convertToDto(Grade grade) {
        return modelMapper.map(grade, GradeDTO.class);
    }
}
