package a0120i1.codegym.cinema_management.dto.login;

import a0120i1.codegym.cinema_management.dto.login.AccountLoginDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    private String id;
    private String fullName;
    private AccountLoginDTO account;

}
