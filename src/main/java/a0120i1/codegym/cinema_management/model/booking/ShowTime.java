package a0120i1.codegym.cinema_management.model.booking;

import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.model.theater.Theater;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    @GeneratedValue(generator = "idShowTime")
    @GenericGenerator(
            name = "idShowTime",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "ST"),
            strategy = "a0120i1.codegym.cinema_management.model.IdGenerator"
    )
    private String id;
    @DateTimeFormat(pattern = "hh:mm")
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