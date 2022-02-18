package a0120i1.codegym.cinema_management.model.booking;

import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.model.theater.Theater;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowTime {
    @Id
    private String id;
    private String movieID;
    private String theaterID;
    private LocalTime startTime;
    private LocalDate startDate;
    private double price;

    @OneToMany(mappedBy = "showTime")
    @JsonIgnore
    private List<Booking> bookingList;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Theater theater;
}