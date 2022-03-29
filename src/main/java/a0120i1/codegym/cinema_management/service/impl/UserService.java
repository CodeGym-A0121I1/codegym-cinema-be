package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.repository.IUserRepository;
import a0120i1.codegym.cinema_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> getById(String id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        user.getAccount().setPassword(passwordEncoder.encode(user.getAccount().getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public User getByUsername(String username) {
        return this.userRepository.findByAccount_Username(username);
    }

    @Override
    public List<User> getAllMember() {
        return this.userRepository.getAllMember();
    }

    @Override
    public List<User> getAllEmployee() {
        return this.userRepository.getAllEmployee();
    }


}