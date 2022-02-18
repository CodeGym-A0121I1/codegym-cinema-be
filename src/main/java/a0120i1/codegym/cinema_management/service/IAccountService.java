package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.model.user.Account;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IAccountService {
    Optional<Account> getById(String username);
}
