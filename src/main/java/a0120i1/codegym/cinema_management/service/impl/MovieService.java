package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.repository.IMovieRepository;
import a0120i1.codegym.cinema_management.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private IMovieRepository iMovieRepository;

    @Override
    public List<Movie> findMovieByOpeningDayBetweenAndEndDay2(LocalDate date) {
        return iMovieRepository.findByDate(date);
    }

    @Override
    public List<Movie> getAll() {
        return iMovieRepository.findAll();
    }

    @Override
    public Optional<Movie> getById(String id) {
        return iMovieRepository.findById(id);
    }

    @Override
    public Movie save(Movie movie) {
        return iMovieRepository.save(movie);
    }

    @Override
    public void deleteById(String id) {
        iMovieRepository.deleteById(id);
    }
}
