package pl.meklad.ipezput2k20.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.model.domain.User;
import pl.meklad.ipezput2k20.repo.UserRepo;

import java.util.Arrays;

/**
 * Create by dev on 06.11.2020
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepo repo;

    @Autowired
    public UserDetailsServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
