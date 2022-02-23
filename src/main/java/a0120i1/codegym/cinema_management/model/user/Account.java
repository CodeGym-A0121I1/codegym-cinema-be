package a0120i1.codegym.cinema_management.model.user;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(role.name()));

        return grantedAuthorityList;
    }
}

