package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.dto.ChangePasswordRequest;
import a0120i1.codegym.cinema_management.model.user.Account;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IAccountService extends IService<Account, String> {

    boolean changePassword(ChangePasswordRequest changePasswordRequest);

    Boolean isUsernameExists(String username);

}
