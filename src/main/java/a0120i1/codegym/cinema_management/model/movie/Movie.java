package a0120i1.codegym.cinema_management.model.movie;

import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(generator = "idMovie")
    @GenericGenerator(
            name = "idMovie",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "M"),
            strategy = "a0120i1.codegym.cinema_management.model.IdGenerator"
    )
    private String id;
    private String name;
    private String poster; // imange    private String trailer;
    private String introduction; // introduce;
    private LocalDate openingDay;
    private LocalDate endDay;
    private LocalTime duration; //minuteTime;
    private String type; //version;
    private String content;

    @ManyToOne
    private Actor actor;

    @ManyToOne
    private Director director;

    @ManyToOne
    private Producer producer;

    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genreList;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<ShowTime> showTimeList;
}
