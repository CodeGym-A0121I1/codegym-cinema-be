package a0120i1.codegym.cinema_management.dto.login;

import a0120i1.codegym.cinema_management.model.user.User;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private final String jwt;
    private final User user;
    private final String status;

}
