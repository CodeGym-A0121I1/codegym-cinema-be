package a0120i1.codegym.cinema_management.model.user;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

