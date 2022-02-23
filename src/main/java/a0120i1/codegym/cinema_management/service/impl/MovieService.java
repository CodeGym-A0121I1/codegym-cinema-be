package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.model.movie.Movie;

import a0120i1.codegym.cinema_management.repository.IMovieRepository;
import a0120i1.codegym.cinema_management.repository.IShowTimeRepository;
import a0120i1.codegym.cinema_management.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Service
public class MovieService implements IMovieService {
@Autowired
    IMovieRepository iMovieRepository;
    @Override
    public List<Movie> findMovieByOpeningDayBetweenAndEndDay2(LocalDate date) {
        return iMovieRepository.findByDate(date);
    }
}
