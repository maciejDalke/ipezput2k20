package pl.meklad.ipezput2k20.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDoneDTO {
    private Long testDoneId;
    private Long userId;
    private Timestamp testDoneTime;
    private Long testGradeId;
    private String description;
    private Long testId;
    private Long newTestId;
}
