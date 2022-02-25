package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.service.IAccountService;
import a0120i1.codegym.cinema_management.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final IUserService userService;
    private final IAccountService accountService;

    public UserController(IUserService userService, IAccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/list-member")
    public ResponseEntity<List<User>> getAllMember() {
        List<User> ListMember = userService.getAll();
        if (ListMember.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(ListMember, HttpStatus.OK);

    }

    @GetMapping("/member/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable("id") String id) {
        Optional<User> user = userService.getById(id);

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping
    public ResponseEntity<User> updateMember(@RequestBody User user){
        Optional<User> currentUser = userService.getById(user.getId());
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
    }




}
