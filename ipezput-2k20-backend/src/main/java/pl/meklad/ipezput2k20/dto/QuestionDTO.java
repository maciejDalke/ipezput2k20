package pl.meklad.ipezput2k20.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.meklad.ipezput2k20.model.enums.QuestionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long questionId;
    private String question;
    private String correctAnswer;
    private String restAnswer;
    private QuestionType type;
    private String url;
    private Long weight;
    private Boolean publicVisibility;
    private Long userId;
    private Long subjectId;

    public QuestionDTO(String question,
                       String correctAnswer,
                       String restAnswer,
                       QuestionType type,
                       String url,
                       Long weight,
                       Boolean publicVisibility,
                       Long userId,
                       Long subjectId) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.restAnswer = restAnswer;
        this.type = type;
        this.url = url;
        this.weight = weight;
        this.publicVisibility = publicVisibility;
        this.userId = userId;
        this.subjectId = subjectId;
    }
}
