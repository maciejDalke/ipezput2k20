package pl.meklad.ipezput2k20.login.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.login.domain.Users;
import pl.meklad.ipezput2k20.login.repo.UsersRepo;
import pl.meklad.ipezput2k20.login.security.jwt.UserToken;

/**
 * Create by dev on 24.10.2020
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsersRepo userRepo;

    @Autowired
    public UserDetailsService(UsersRepo userRepo) {
        this.userRepo = userRepo;
    }

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Users user = userRepo.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UserToken currentUser;
        if (user.getActivate() == true){
            currentUser = new UserToken(user);
        }
        else{
            throw new DisabledException("User is not activated (Disabled User)");
            //If pending activation return a disabled user
            //currentUser = new TokenUser(user, false);
        }
        detailsChecker.check(currentUser);
        return currentUser;
    }
}
