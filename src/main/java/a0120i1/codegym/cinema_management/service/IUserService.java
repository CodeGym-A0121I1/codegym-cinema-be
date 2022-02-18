package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.model.user.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends IService<User, String> {

    User getByUsername(String username);
}
