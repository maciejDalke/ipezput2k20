package pl.meklad.ipezput2k20.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name = "testQuestionSeq", sequenceName = "test_question_seq", allocationSize = 1, schema = "public")
@Table(name = "tests_questions")
public class TestQuestions {
    @Id
    @GeneratedValue(generator = "testQuestionSeq")
    @Column(name = "question_4_test_id")
    private Long question4TestId;

    @Column(name = "test_id")
    private Long testId;

    @Column(name = "question_id")
    private Long questionId;
}
