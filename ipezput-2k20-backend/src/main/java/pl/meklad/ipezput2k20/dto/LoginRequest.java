package pl.meklad.ipezput2k20.dto;

import lombok.*;

/**
 * Create by dev on 06.11.2020
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequest {
    private String username;
    private String password;
}
