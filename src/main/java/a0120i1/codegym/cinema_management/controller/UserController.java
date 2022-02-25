package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.dto.ChangePasswordRequest;
import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.service.impl.AccountService;
import a0120i1.codegym.cinema_management.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final AccountService accountService;

    public UserController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userList = userService.getAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id){
        Optional<User> user = userService.getById(id);
        return user.map(value -> new ResponseEntity<>(value,HttpStatus.OK))
                    .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        Optional<User> userOptional = userService.getById(user.getId());
        if(!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
    }

    @PutMapping("/account/password")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest ){
        boolean isSuccessful = this.accountService.changePassword(changePasswordRequest);
        if (isSuccessful){
            return ResponseEntity.ok(true);
        }else {
            return ResponseEntity.badRequest().body(false);
        }
    }
}
