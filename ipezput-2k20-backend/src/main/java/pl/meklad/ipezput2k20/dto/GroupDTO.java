package pl.meklad.ipezput2k20.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private Long groupId;
    private String name;
    private Long subjectId;

    public GroupDTO(String name, Long subjectId) {
        this.name = name;
        this.subjectId = subjectId;
    }
}
