package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.model.movie.Genre;
import a0120i1.codegym.cinema_management.model.movie.Movie;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface IMovieService extends IService<Movie, String> {

    List<Movie> findMovieByOpeningDayBetweenAndEndDay2(LocalDate date);

    List<Genre> getAllGenre();
}
