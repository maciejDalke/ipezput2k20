package pl.meklad.ipezput2k20.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name = "groupSeq", sequenceName = "group_seq", allocationSize = 1, schema = "public")
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(generator = "groupSeq")
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "name")
    private String name;

    @Column(name = "subject_id")
    private Long subjectId;
}
