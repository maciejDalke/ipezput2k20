package pl.meklad.ipezput2k20.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.meklad.ipezput2k20.model.enums.GroupRole;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleInGroupDTO {
    private Long roleInGroupId;
    private Long userId;
    private Long groupId;
    private GroupRole groupRole;

    public RoleInGroupDTO(Long userId, Long groupId, GroupRole groupRole) {
        this.userId = userId;
        this.groupId = groupId;
        this.groupRole = groupRole;
    }
}
