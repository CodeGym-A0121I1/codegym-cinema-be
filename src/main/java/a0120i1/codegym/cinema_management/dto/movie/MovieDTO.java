package a0120i1.codegym.cinema_management.dto.movie;

import a0120i1.codegym.cinema_management.model.movie.Director;
import a0120i1.codegym.cinema_management.model.movie.Genre;
import a0120i1.codegym.cinema_management.model.movie.Producer;
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

    private String type;

    private String content;

    private List<Genre> genreList;

    private Director director;

    private Producer producer;
}