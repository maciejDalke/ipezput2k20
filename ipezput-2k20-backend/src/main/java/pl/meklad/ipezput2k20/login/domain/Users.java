package pl.meklad.ipezput2k20.login.domain;

import lombok.Data;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.meklad.ipezput2k20.login.enums.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Data
@SequenceGenerator(name = "userSeq", sequenceName = "user_seq", allocationSize = 1, schema = "public")
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(generator = "userSeq")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Unique
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
    @Unique
    @Size(min = 4,max = 30,message = "Invalid userName")
    @Column(name = "user_name")
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;


}
