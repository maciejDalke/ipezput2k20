package pl.meklad.ipezput2k20.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.meklad.ipezput2k20.model.domain.Question;
import pl.meklad.ipezput2k20.model.enums.QuestionType;

import java.util.List;

/**
 * Create by dev on 24.10.2020
 */
@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
    List<Question> findAllBySubjectIdAndType (Long subjectId, QuestionType type);
}
