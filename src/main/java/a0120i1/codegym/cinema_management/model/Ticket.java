package a0120i1.codegym.cinema_management.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id

    private String id;

    @ManyToOne
    private Booking booking;

    @OneToOne
    private Seat seat;

    private Long price;

}