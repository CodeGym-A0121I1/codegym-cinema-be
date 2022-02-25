package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, String> {

    Boolean existsByUsername(String username);

}