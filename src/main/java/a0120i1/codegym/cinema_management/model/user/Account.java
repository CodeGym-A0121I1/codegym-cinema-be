package a0120i1.codegym.cinema_management.model.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    private boolean enable;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private User user;

    public boolean getEnable() {
        return this.enable;
    }
}

