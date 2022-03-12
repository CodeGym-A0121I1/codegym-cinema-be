package a0120i1.codegym.cinema_management.model.theater;

import a0120i1.codegym.cinema_management.model.booking.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(generator = "idSeat")
    @GenericGenerator(
            name = "idSeat",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "S"),
            strategy = "a0120i1.codegym.cinema_management.model.IdGenerator"
    )
    private String id;

    private String name;

    @ManyToOne
    private Theater theater;

    @OneToMany(mappedBy = "seat")
    @JsonIgnore
    private List<Ticket> ticketList;

}