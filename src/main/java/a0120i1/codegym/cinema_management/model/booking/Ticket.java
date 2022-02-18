package a0120i1.codegym.cinema_management.model.booking;

import a0120i1.codegym.cinema_management.model.theater.Seat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(generator = "idTicket")
    @GenericGenerator(
            name = "idTicket",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "T"),
            strategy = "a0120i1.codegym.cinema_management.model.IdGenerator"
    )
    private String id;

    @ManyToOne
    private Booking booking;

    @ManyToOne
    private Seat seat;

    private Long price;

}