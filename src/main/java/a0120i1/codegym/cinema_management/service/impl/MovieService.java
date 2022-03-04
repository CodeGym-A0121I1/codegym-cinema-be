package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.model.movie.Genre;
import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.repository.IGenreRepository;
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

    @Autowired
    private IGenreRepository genreRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getById(String id) {
        return Optional.empty();
    }

    @Override
    public Movie save(Movie entity) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<Movie> getAllMovieByName(String name) {
        return movieRepository.findAllByNameContains(name);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}