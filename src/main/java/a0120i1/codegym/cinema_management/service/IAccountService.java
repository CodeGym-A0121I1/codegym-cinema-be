package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.dto.user.ChangePasswordRequest;
import a0120i1.codegym.cinema_management.model.user.Account;
import org.springframework.stereotype.Service;

@Service
public interface IAccountService extends IService<Account, String> {


    Boolean isUsernameExists(String username);


    boolean changePassword(ChangePasswordRequest changePasswordRequest);

    Boolean existsByUsername(String username);

    Boolean sendOtpToEmail(String toEmail, String otp);


}
