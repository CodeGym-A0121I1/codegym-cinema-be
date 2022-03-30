package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {

    private final IUserService userService;

    public EmployeeController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllEmployee() {
        List<User> employeeList = userService.getAllEmployee();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable("id") String id) {
        Optional<User> currentEmployee = userService.getById(id);
        if (!currentEmployee.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentEmployee.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> updateEmployee(@RequestBody User user) {
        Optional<User> currentEmployee = userService.getById(user.getId());
        if (!currentEmployee.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }
}
