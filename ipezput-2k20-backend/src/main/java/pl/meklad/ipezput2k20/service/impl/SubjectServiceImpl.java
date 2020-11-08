package pl.meklad.ipezput2k20.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.SubjectDTO;
import pl.meklad.ipezput2k20.model.domain.Subject;
import pl.meklad.ipezput2k20.repo.SubjectRepo;
import pl.meklad.ipezput2k20.service.SubjectService;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create by dev on 06.11.2020
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    private final ModelMapper modelMapper;
    private final SubjectRepo subjectRepo;

    @Autowired
    public SubjectServiceImpl(ModelMapper modelMapper, SubjectRepo subjectRepo) {
        this.modelMapper = modelMapper;
        this.subjectRepo = subjectRepo;
    }

    @Override
    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        Subject subject = convertToEntity(subjectDTO);
        return convertToDto(subjectRepo.save(subject));
    }

    @Override
    public Iterable<SubjectDTO> findAllTest() {
        return subjectRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<SubjectDTO> findSubjectBySubjectId(Long subjectId) {
        return Optional.of(convertToDto(subjectRepo.findById(subjectId).orElseThrow()));
    }

    @Override
    public Optional<SubjectDTO> findSubjectByName(String name) {
        return Optional.of(convertToDto(subjectRepo.findAllByName(name).orElseThrow()));
    }

    @Override
    public SubjectDTO updateSubject(SubjectDTO subjectDTO, Long subjectId) {
        if (!subjectRepo.existsById(subjectId))
            return null;
        else {
            Subject subject = convertToEntity(subjectDTO);
            subject.setSubjectId(subjectId);
            return convertToDto(subjectRepo.save(subject));
        }
    }

    @Override
    public boolean deleteSubjectBySubjectId(Long subjectId) {
        if (!subjectRepo.existsById(subjectId))
            return false;
        else {
            subjectRepo.deleteById(subjectId);
            return true;
        }
    }

    //======================================================================================================================
    private Subject convertToEntity(SubjectDTO subjectDTO) {
        return modelMapper.map(subjectDTO, Subject.class);
    }

    private SubjectDTO convertToDto(Subject subject) {
        return modelMapper.map(subject, SubjectDTO.class);
    }
}
