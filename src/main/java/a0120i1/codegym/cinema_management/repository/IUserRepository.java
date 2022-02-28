package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User, String> {
    User findByAccount_Username(String username);



}