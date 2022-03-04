package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.user.ERole;
import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import a0120i1.codegym.cinema_management.dto.ChangePasswordRequest;
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
}
