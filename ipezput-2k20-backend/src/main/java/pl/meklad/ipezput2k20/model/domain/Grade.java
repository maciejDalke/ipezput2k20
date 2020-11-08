package pl.meklad.ipezput2k20.model.domain;

import com.sun.istack.Nullable;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.meklad.ipezput2k20.model.enums.GradeType;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@SequenceGenerator(name = "gradeSeq", sequenceName = "grades_seq", allocationSize = 1, schema = "public")
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(generator = "gradeSeq")
    @Column(name = "grade_id")
    private Long gradeId;

    @Column(name = "grade")
    private String grade;

    @Column(name = "user_id")
    private Long userId;

    @Nullable
    @Column(name = "test_id")
    private Long testId;

    @Nullable
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "description")
    private String description;

    @Enumerated
    @Column(name = "type")
    private GradeType type;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "group_id")
    private Long groupId;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    @UpdateTimestamp
    @Column(name = "modify_time")
    private Timestamp modifyTime;

    @Column(name = "is_active")
    private Boolean isActive;
}
