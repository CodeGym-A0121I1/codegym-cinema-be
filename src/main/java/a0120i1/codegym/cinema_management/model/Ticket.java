package a0120i1.codegym.cinema_management.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Ticket {

    @Id
    private String id;
    private String bookingId;
    private String chairId;
    private Long price;

}