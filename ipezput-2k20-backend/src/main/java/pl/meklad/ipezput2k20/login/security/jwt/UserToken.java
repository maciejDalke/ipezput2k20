package pl.meklad.ipezput2k20.login.security.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import pl.meklad.ipezput2k20.login.domain.Users;

/**
 * Create by dev on 24.10.2020
 */

public class UserToken extends User {
    private Users user;

    public UserToken(Users user) {
        super(String.valueOf(user.getUserId()), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserRole().toString()));
        this.user = user;
    }

    public Users getUser() {
        return user;
    }

    public String getRole() {
        return user.getUserRole().toString();
    }
}
