package pl.meklad.ipezput2k20.service;

import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.dto.GradeDTO;

import java.util.Optional;

/**
 * Create by dev on 07.11.2020
 */
@Service
public interface GradeService {

    GradeDTO createGrade(GradeDTO gradeDTO);

    Iterable<GradeDTO> findAllGrades();
    Optional<GradeDTO> findByGradeId(Long gradeId);
    Optional<GradeDTO> findByUserId(Long userId);

    GradeDTO updateGrade(GradeDTO gradeDTO, Long gradeId);

    boolean deleteTestByTestId(Long gradeId);
}
