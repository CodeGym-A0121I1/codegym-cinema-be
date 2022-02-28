package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.dto.user.ChangePasswordRequest;
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

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public boolean changePassword(ChangePasswordRequest changePasswordRequest) {
        Optional<Account> accountOptional = this.getById(changePasswordRequest.getUsername());
        return accountOptional.map(account -> {
            if (passwordEncoder.matches(changePasswordRequest.getOldPassword(), account.getPassword()) && !passwordEncoder.matches(changePasswordRequest.getNewPassword(), account.getPassword())) {
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
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Boolean existsByUsername(String username) {
        return this.accountRepository.existsByUsername(username);
    }

    @Override
    public Boolean getEnableByUsername(String username) {
        return this.accountRepository.getEnableByUsername(username);
    }

    @Override
    public Boolean sendOtpToEmail() {
        return null;
    }

    @Override
    public Boolean forgotPassword() {

        return null;
    }
}
