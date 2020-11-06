package pl.meklad.ipezput2k20.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {
    private Long testId;
    private String name;
    private Long userId;
    private Long groupId;
    private Timestamp deadline;
    private Timestamp createdTime;
}
