package a0120i1.codegym.cinema_management.model.user;

import a0120i1.codegym.cinema_management.model.booking.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Enumerated(EnumType.STRING)
    private EGender gender;

    private LocalDate dayOfBirth;

    private String address;

    private String idCard;

    private String image;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToOne(cascade =CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Booking> bookingList;
}