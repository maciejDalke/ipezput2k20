package pl.meklad.ipezput2k20.model.domain;

import lombok.Data;
import pl.meklad.ipezput2k20.model.enums.GroupRole;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name = "roleInGroupSeq", sequenceName = "role_in_group_seq", allocationSize = 1, schema = "public")
@Table(name = "role_in_group")
public class RoleInGroup {
    @Id
    @GeneratedValue(generator = "roleInGroupSeq")
    @Column(name = "role_in_group_id")
    private Long roleInGroupId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "group_id")
    private Long groupId;

    @Enumerated
    @Column(name = "group_role")
    private GroupRole groupRole;
}
