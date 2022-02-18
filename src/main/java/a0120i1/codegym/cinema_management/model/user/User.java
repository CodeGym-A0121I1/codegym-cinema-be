package a0120i1.codegym.cinema_management.model.user;

import a0120i1.codegym.cinema_management.model.booking.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "idUser")
    @GenericGenerator(
            name = "idUser",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "U"),
            strategy = "a0120i1.codegym.cinema_management.model.IdGenerator"
    )
    private String id;
    private String fullName;

    private String email;

    private String phoneNumber;

    private Boolean gender;

    private LocalDate dayOfBirth;

    private String address;

    private String idCard;

    private String image;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToOne
    @JsonIgnore
    private Account account;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Booking> bookingList;
}