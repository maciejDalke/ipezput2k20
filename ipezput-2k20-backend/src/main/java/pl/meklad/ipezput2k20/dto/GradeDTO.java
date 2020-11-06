package pl.meklad.ipezput2k20.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.meklad.ipezput2k20.model.enums.GradeType;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
    private Long gradeId;
    private String grade;
    private Long userId;
    private Long testId;
    private Long reportId;
    private String description;
    private GradeType type;
    private Long weight;
    private Long groupId;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private Boolean isActive;

    public GradeDTO(String grade,
                    Long userId,
                    Long testId,
                    Long reportId,
                    String description,
                    GradeType type,
                    Long weight,
                    Long groupId,
                    Boolean isActive) {
        this.grade = grade;
        this.userId = userId;
        this.testId = testId;
        this.reportId = reportId;
        this.description = description;
        this.type = type;
        this.weight = weight;
        this.groupId = groupId;
        this.isActive = isActive;
    }
}
