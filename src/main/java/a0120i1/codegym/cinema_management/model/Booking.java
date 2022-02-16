package a0120i1.codegym.cinema_management.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(generator = "idBooking")
    @GenericGenerator(
            name = "idBooking",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "B"),
            strategy = "a0120i1.codegym.cinema_management.model.IdGenerator"
    )
    private String id;

    private
}
