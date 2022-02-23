package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.dto.ChangePasswordRequest;
import a0120i1.codegym.cinema_management.model.user.Account;
import org.springframework.stereotype.Service;

@Service
public interface IAccountService extends IService<Account,String>{
    boolean changePassword(ChangePasswordRequest changePasswordRequest);
}
