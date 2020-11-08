package pl.meklad.ipezput2k20.service;

import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.SubjectDTO;

import java.util.Optional;

/**
 * Create by dev on 13.10.2020
 */
@Service
public interface SubjectService {

    SubjectDTO createSubject(SubjectDTO subjectDTO);

    Iterable<SubjectDTO> findAllTest();
    Optional<SubjectDTO> findSubjectBySubjectId(Long subjectId);
    Optional<SubjectDTO> findSubjectByName(String name);

    SubjectDTO updateSubject(SubjectDTO subjectDTO, Long subjectId);

    boolean deleteSubjectBySubjectId(Long subjectId);
}
