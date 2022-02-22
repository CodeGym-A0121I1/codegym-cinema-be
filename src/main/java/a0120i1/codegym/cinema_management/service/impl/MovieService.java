package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.repository.IMovieRepository;
import a0120i1.codegym.cinema_management.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepository movieRepository;


    @Override
    public Movie finById(String id) {
        return  movieRepository.findById(id).orElse(null);
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }
}
