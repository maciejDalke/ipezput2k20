package pl.meklad.ipezput2k20.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name = "subjectSeq", sequenceName = "subject_seq", allocationSize = 1, schema = "public")
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(generator = "subjectSeq")
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "name")
    private String name;

    @Column(name = "shortcut")
    private String shortcut;
}
