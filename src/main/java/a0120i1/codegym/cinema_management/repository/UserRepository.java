package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}