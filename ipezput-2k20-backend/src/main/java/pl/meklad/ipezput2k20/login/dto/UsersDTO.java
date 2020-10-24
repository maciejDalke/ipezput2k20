package pl.meklad.ipezput2k20.login.dto;

import lombok.Data;
import pl.meklad.ipezput2k20.login.enums.UserRole;

import java.sql.Timestamp;

@Data
public class UsersDTO {
    private Long userId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Long studentId;
    private Boolean activate;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private String userName;
    private UserRole userRole;

    public UsersDTO() {
    }

    public UsersDTO(String name,
                    String surname,
                    String email,
                    String password,
                    Long studentId,
                    Boolean activate,
                    String userName,
                    UserRole userRole) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.studentId = studentId;
        this.activate = activate;
        this.userName = userName;
        this.userRole = userRole;
    }

    public UsersDTO(Long userId,
                    String name,
                    String surname,
                    String email,
                    String password,
                    Long studentId,
                    Boolean activate,
                    String userName,
                    UserRole userRole) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.studentId = studentId;
        this.activate = activate;
        this.userName = userName;
        this.userRole = userRole;
    }
}
