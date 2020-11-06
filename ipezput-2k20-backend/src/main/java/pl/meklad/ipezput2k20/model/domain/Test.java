package pl.meklad.ipezput2k20.model.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@SequenceGenerator(name = "testSeq", sequenceName = "test_seq", allocationSize = 1, schema = "public")
@Table(name = "tests")
public class Test {
    @Id
    @GeneratedValue(generator = "testSeq")
    @Column(name = "test_id")
    private Long testId;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "deadline")
    private Timestamp deadline;

    @CreationTimestamp
    @Column(name = "created_time")
    private Timestamp createdTime;
}
