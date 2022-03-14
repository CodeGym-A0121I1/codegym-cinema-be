package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.model.movie.*;
import a0120i1.codegym.cinema_management.repository.*;
import a0120i1.codegym.cinema_management.model.movie.Genre;
import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.repository.IGenreRepository;
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

    @Autowired
    private IGenreRepository genreRepository;

    @Autowired
    private IShowTimeRepository showTimeRepository;

    @Autowired
    private IActorRepository actorRepository ;

    @Autowired
    private IProducerRepository producerRepository ;

    @Autowired
    private IDirectorRepository directorRepository ;

    @Autowired
    private IGenreRepository genreRepository;

    @Override
    public List<Movie> findMovieByOpeningDayBetweenAndEndDay2(LocalDate date) {
        return iMovieRepository.findByDate(date);
    }

    @Override
    public List<Actor> getAllActor() {
        return actorRepository.findAll();
    }

    @Override
    public List<Director> getAllDirector() {
        return directorRepository.findAll() ;
    }

    @Override
    public List<Producer> getAllProducer() {
        return producerRepository.findAll() ;
    }

    @Override
    public List<Movie> findAllByNameAndGenre(String name, int genre) {
        return iMovieRepository.findAllByNameContainsAndGenre(name, genre);
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

    @Override
    public List<Movie> getAllMovieByName(String name) {
        return iMovieRepository.findAllByNameContains(name);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}