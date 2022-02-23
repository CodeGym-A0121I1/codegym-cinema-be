package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.dto.ChangePasswordRequest;
import a0120i1.codegym.cinema_management.model.user.Account;
import a0120i1.codegym.cinema_management.repository.IAccountRepository;
import a0120i1.codegym.cinema_management.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService implements IAccountService {

//    private final IAccountRepository accountRepository;
//
//
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public AccountService(IAccountRepository accountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.accountRepository = accountRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public boolean changePassword(ChangePasswordRequest changePasswordRequest) {
        Optional<Account> accountOptional = this.getById(changePasswordRequest.getUsername());
        return accountOptional.map(account -> {
            if(passwordEncoder.matches(changePasswordRequest.getOldPassword(),account.getPassword())){
                account.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                accountRepository.save(account);
                return true;
            }
            return false;
        }).orElse(false);
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Optional<Account> getById(String username) {
        return accountRepository.findById(username);
    }

    @Override
    public Account save(Account entity) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Boolean isUsernameExists(String username) {
        return this.accountRepository.existsByUsername(username);
    }
}
