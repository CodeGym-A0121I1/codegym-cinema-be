package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.user.Account;
import a0120i1.codegym.cinema_management.model.user.ERole;
import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.security.service.OtpService;
import a0120i1.codegym.cinema_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private String otp;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.getAccount().setPassword(passwordEncoder.encode(user.getAccount().getPassword()));
        user.getAccount().setEnable(true);
        user.getAccount().setRole(ERole.ROLE_USER);
        return userService.save(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
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

    @GetMapping("account/generate/{username}")
    public ResponseEntity<Boolean> generateOtp(@PathVariable("username") String username) {

        Optional<Account> accountOptional = this.accountService.getById(username);

        //return:
        // true -> ok
        // false -> Account locked
        // null -> Username not exists

        return accountOptional.map(account -> {
            if (account.getEnable()) {
                this.otp = this.otpService.generateOTP();
                try {
                    this.accountService.sendOtpToEmail("votrungtrongqn99@gmail.com", otp);

                } catch (Exception e) {
                    throw e;
                }
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
    }

    @GetMapping("account/forgot-password/{username}/{newPassword}/{otp}")
    public ResponseEntity<Boolean> forgotPassword(@PathVariable("username") String username,
                                                  @PathVariable("newPassword") String newPassword,
                                                  @PathVariable("otp") String otp) {
        Optional<Account> accountOptional = this.accountService.getById(username);

        return accountOptional.map(account -> {
            if (otp.equals(this.otp)) {
                account.setPassword(this.passwordEncoder.encode(newPassword));
                this.accountService.save(account);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
    }

}
