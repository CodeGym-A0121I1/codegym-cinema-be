package a0120i1.codegym.cinema_management.security.service;

import a0120i1.codegym.cinema_management.model.user.Account;
import a0120i1.codegym.cinema_management.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IAccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = this.accountService.getById(username);
        return accountOptional.map(MyUserDetails::new).orElse(null);
    }
}
