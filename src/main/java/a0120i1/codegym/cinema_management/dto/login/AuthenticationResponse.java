package a0120i1.codegym.cinema_management.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private String jwt;
    private UserLoginDTO user;
    private String status;

}
