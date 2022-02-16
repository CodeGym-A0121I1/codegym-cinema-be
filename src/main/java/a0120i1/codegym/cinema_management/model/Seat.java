package a0120i1.codegym.cinema_management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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

    @OneToOne(mappedBy = "seat")
    private Ticket ticket;
}