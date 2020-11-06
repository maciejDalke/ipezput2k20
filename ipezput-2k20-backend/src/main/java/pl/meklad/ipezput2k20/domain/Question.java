package pl.meklad.ipezput2k20.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name = "questionSeq", sequenceName = "question_seq", allocationSize = 1, schema = "public")
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(generator = "questionSeq")
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question")
    private String question;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(name = "rest_answer")
    private String restAnswer;

    @Enumerated
    @Column(name = "type")
    private QuestionType type;

    @Column(name = "url")
    private String url;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "public_visibility")
    private Boolean publicVisibility;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "subject_id")
    private Long subjectId;
}
