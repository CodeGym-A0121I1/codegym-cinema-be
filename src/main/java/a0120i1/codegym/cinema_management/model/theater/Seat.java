package a0120i1.codegym.cinema_management.model.theater;

import a0120i1.codegym.cinema_management.model.booking.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    private String id;

    private String name;

    @ManyToOne
    private Theater theater;

    @OneToMany(mappedBy = "seat")
    @JsonIgnore
    private List<Ticket> ticketList;
}