package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.model.user.Account;
import a0120i1.codegym.cinema_management.repository.IAccountRepository;
import a0120i1.codegym.cinema_management.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;


    @Override
    public Optional<Account> getById(String username) {
        return this.accountRepository.findById(username);
    }

    @Override
    public Boolean isUsernameExists(String username) {
        return this.accountRepository.existsByUsername(username);
    }
}
