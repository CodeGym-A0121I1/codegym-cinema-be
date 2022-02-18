package a0120i1.codegym.cinema_management.service.Impl;

import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.repository.UserRepository;
import a0120i1.codegym.cinema_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
