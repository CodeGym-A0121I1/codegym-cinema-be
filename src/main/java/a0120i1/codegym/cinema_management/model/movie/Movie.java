package a0120i1.codegym.cinema_management.model.movie;

import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import a0120i1.codegym.cinema_management.model.theater.ETypeTheater;
import a0120i1.codegym.cinema_management.model.theater.Theater;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String trailer; // introduce->trailer;
    private LocalDate openingDay;
    private LocalDate endDay;
    private Integer duration; //localTime->Interger;

    @Enumerated(EnumType.STRING)
    private ETypeTheater type;//version;

    private String content;

    @ManyToMany(fetch = FetchType.EAGER)//many-
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actorList;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "movie_director",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private List<Director> directorList;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "movie_producer",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id"))
    private List<Producer> producerList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genreList;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<ShowTime> showTimeList;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<Theater> theaterList;
}
