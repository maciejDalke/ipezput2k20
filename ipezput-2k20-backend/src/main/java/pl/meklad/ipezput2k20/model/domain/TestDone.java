package pl.meklad.ipezput2k20.model.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@SequenceGenerator(name = "testDoneSeq", sequenceName = "test_done_seq", allocationSize = 1, schema = "public")
@Table(name = "tests_done")
public class TestDone {
    @Id
    @GeneratedValue(generator = "testDoneSeq")
    @Column(name = "test_done_id")
    private Long testDoneId;

    @Column(name = "user_id")
    private Long userId;

    @CreationTimestamp
    @Column(name = "test_done_time")
    private Timestamp testDoneTime;

    @Column(name = "test_grade_id")
    private Long testGradeId;

    @Column(name = "description")
    private String description;

    @Column(name = "test_id")
    private Long testId;

    @Column(name = "new_test_id")
    private Long newTestId;
}
