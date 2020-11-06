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

    @Column(name = "type")
    private String type;

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

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getRestAnswer() {
        return this.restAnswer;
    }

    public void setRestAnswer(String restAnswer) {
        this.restAnswer = restAnswer;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getWeight() {
        return this.weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Boolean getPublicVisibility() {
        return this.publicVisibility;
    }

    public void setPublicVisibility(Boolean publicVisibility) {
        this.publicVisibility = publicVisibility;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
