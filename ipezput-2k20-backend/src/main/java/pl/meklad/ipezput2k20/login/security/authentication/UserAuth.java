package pl.meklad.ipezput2k20.login.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import pl.meklad.ipezput2k20.login.security.jwt.UserToken;

import java.util.Collection;

/**
 * Create by dev on 24.10.2020
 */

public class UserAuth implements Authentication {
    private final UserToken user;
    private boolean authenticated = true;

    public UserAuth(UserToken user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
