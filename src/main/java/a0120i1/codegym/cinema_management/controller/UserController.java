package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.dto.user.ForgotPassword;
import a0120i1.codegym.cinema_management.model.user.Account;
import a0120i1.codegym.cinema_management.model.user.ERole;
import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.security.service.OtpService;
import a0120i1.codegym.cinema_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import a0120i1.codegym.cinema_management.dto.user.ChangePasswordRequest;
import a0120i1.codegym.cinema_management.service.impl.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.getAccount().setPassword(passwordEncoder.encode(user.getAccount().getPassword()));
        user.getAccount().setEnable(true);
        user.getAccount().setRole(ERole.ROLE_USER);
        return userService.save(user);
    }

    @GetMapping
    public ResponseEntity<?> getAllUser() {
        List<User> userList = userService.getAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        Optional<User> user = userService.getById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> userOptional = userService.getById(user.getId());
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @PutMapping("/account/password")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        boolean isSuccessful = this.accountService.changePassword(changePasswordRequest);
        if (isSuccessful) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("account/generate-otp/{username}")
    public ResponseEntity<Boolean> generateOtp(@PathVariable("username") String username) {

        Optional<Account> accountOptional = this.accountService.getById(username);

        return accountOptional.map(account -> {
            if (account.getEnable()) {
                String otp = this.otpService.generateOTP(username);
                boolean isSendOtp = this.accountService.sendOtpToEmail(account.getUser().getEmail(), otp);
                if (isSendOtp) {
                    return new ResponseEntity<>(true, HttpStatus.OK);// Send mail success
                } else  {
                    return new ResponseEntity<>(false, HttpStatus.OK);  // Send mail fail
                }
            } else {
                return new ResponseEntity<>(true, HttpStatus.BAD_REQUEST);  // Account locked
            }
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST)); // Username not exists
    }

    @PostMapping("account/forgot-password")
    public ResponseEntity<Boolean> forgotPassword(@RequestBody ForgotPassword forgotPassword) {

        Optional<Account> accountOptional = this.accountService.getById(forgotPassword.getUsername());

        return accountOptional.map(account -> {
            String otpServer = this.otpService.getOtp(forgotPassword.getUsername());
            if (forgotPassword.getOtp().equals(otpServer)) {
                account.setPassword(this.passwordEncoder.encode(forgotPassword.getNewPassword()));
                this.accountService.save(account);
                this.otpService.clearOTP(forgotPassword.getUsername());
                return new ResponseEntity<>(true, HttpStatus.OK); // Success
            } else {
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST); // OTP fail
            }
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST)); // username not exists

    }

}
