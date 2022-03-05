package a0120i1.codegym.cinema_management.dto.movie;

import a0120i1.codegym.cinema_management.model.movie.Actor;
import a0120i1.codegym.cinema_management.model.movie.Director;
import a0120i1.codegym.cinema_management.model.movie.Genre;
import a0120i1.codegym.cinema_management.model.movie.Producer;
import a0120i1.codegym.cinema_management.model.theater.ETypeTheater;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private String id;

    private String name;

    private String poster;

    private String trailer;

    private String introduction;

    private LocalDate openingDay;

    private LocalDate endDay;

    private LocalTime duration;

    private ETypeTheater type;

    private String content;

    private List<Genre> genreList;

    private List<Actor> actorList;

    private List<Director> directorList;

    private List<Producer> producerList;

    private List<ShowTimeDTO> showTimeList;

    private List<TheaterDTO> theaterList;
}