package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.dto.user.ChangePasswordRequest;
import a0120i1.codegym.cinema_management.model.user.Account;
import a0120i1.codegym.cinema_management.repository.IAccountRepository;
import a0120i1.codegym.cinema_management.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;


@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;


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
    public Boolean sendOtpToEmail(String email, String otp) {
        try {
            MimeMessage message = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(email);
            helper.setSubject("MÃ OTP - ĐỔI MẬT KHẨU");
            helper.setText("<h3>Xin chao ! </h3>" +
                    "<p>Vui long khong chia se ma nay cho bat ky ai.</p>" +
                    "<p>Ma OTP cua ban la: <span style='color: blue; font-size: x-large'>" + otp + "</span></p>" +
                    "<p>Link dan den trang chu: <a style='color: red; text-decoration: underline' href='http://localhost:4200'>bam vao day</a></p>", true
            );
            System.out.println("Send OTP to mail success !!!");
            this.javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
