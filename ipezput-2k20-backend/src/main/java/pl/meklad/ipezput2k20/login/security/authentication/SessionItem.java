package pl.meklad.ipezput2k20.login.security.authentication;

import lombok.Data;

import java.util.List;

/**
 * Create by dev on 24.10.2020
 */
@Data
public class SessionItem {
    private String token;
    private Long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private List<String> userRole;
}
