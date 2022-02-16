package a0120i1.codegym.cinema_management.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Account {

    @Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    private boolean enable;

    @OneToOne(mappedBy = "account")
    private User user;

    public boolean getEnable() {
        return this.enable;
    }
}

