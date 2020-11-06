package pl.meklad.ipezput2k20.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.meklad.ipezput2k20.model.enums.UserRole;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long studentId;
    private Boolean activate;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private String username;
    private UserRole userRole;
    private String realPassword;

    public UserDTO(String firstName,
                   String lastName,
                   String email,
                   String password,
                   Long studentId,
                   Boolean activate,
                   String username,
                   UserRole userRole,
                   String realPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.studentId = studentId;
        this.activate = activate;
        this.username = username;
        this.userRole = userRole;
        this.realPassword = realPassword;
    }
}
