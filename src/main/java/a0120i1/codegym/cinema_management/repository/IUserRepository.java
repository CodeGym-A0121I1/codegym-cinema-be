package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, String> {
    User findByAccount_Username(String username);
    @Query(value = "select * from user as u inner join account as a on u.account_username = a.username\n" +
            "where a.role = 'ROLE_USER'",nativeQuery = true)
    List<User> getAllMember();


}