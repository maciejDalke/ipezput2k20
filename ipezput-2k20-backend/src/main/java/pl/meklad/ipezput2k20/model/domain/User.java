package pl.meklad.ipezput2k20.model.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.meklad.ipezput2k20.model.enums.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Entity
@SequenceGenerator(name = "userSeq", sequenceName = "user_seq", allocationSize = 1, schema = "public")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "userSeq")
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "activate")
    private Boolean activate;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    @UpdateTimestamp
    @Column(name = "modify_time")
    private Timestamp modifyTime;

    @NotNull
    @Size(min = 5, max = 30, message = "niedozwolona wielkość")
    @Column(name = "username")
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Column(name = "real_password")
    private String realPassword;
}
