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

    private String poster;

    private String trailer;

    private String introduction;

    private LocalDate openingDay;

    private LocalDate endDay;

    private LocalTime duration;

    private String type;

    private String content;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actorList;

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