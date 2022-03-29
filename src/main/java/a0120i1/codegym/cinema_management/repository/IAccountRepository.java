package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAccountRepository extends JpaRepository<Account, String> {

    Boolean existsByUsername(String username);

    @Query("select a.enable from Account a where a.username = :username")
    Boolean getEnableByUsername(@Param("username") String username);

    @Query(value = "SELECT role FROM `user` u " +
            "INNER JOIN account a ON u.account_username = a.username " +
            "WHERE u.id = :userId AND role = \"ROLE_USER\"", nativeQuery = true)
    String getRoleByUserId(@Param("userId") String userId);
}