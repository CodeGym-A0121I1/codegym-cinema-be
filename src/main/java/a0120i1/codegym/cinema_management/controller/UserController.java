package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody User user) {
        this.userService.save(user);
        return new ResponseEntity<>("Tao thanh cong", HttpStatus.OK);
    }
}
