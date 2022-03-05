package a0120i1.codegym.cinema_management.dto.movie;

import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import a0120i1.codegym.cinema_management.model.movie.Actor;
import a0120i1.codegym.cinema_management.model.movie.Director;
import a0120i1.codegym.cinema_management.model.movie.Genre;
import a0120i1.codegym.cinema_management.model.movie.Producer;
import a0120i1.codegym.cinema_management.model.theater.ETypeTheater;
import a0120i1.codegym.cinema_management.model.theater.Theater;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private String id;
    private String name;
    private String poster;
    private String trailer ;
    private LocalDate openingDay;
    private LocalDate endDay;
    private Integer duration;
    private ETypeTheater type;
    private String content;

    private List<Actor> actorList;

    private List<Director> directorList;

    private List<Producer> producerList;

    private List<Genre> genreList;

    private List<ShowTimeDTO> showTimeList;

    private List<TheaterDTO> theaterList;
}
