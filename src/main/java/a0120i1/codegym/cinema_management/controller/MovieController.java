package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.dto.movie.MovieDTO;
import a0120i1.codegym.cinema_management.model.movie.Genre;
import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.service.IMovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/movie")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovie(@RequestParam(required = false) String name) {
        List<Movie> movieList = new ArrayList<>();
        if (name != null) {
            movieList = movieService.getAllMovieByName(name);
        } else {
            movieList = movieService.getAll();
        }

        if (movieList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(movieList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));

    }

    public Movie convertToEntity(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);

        return movie;
    }

    public MovieDTO convertToDTO(Movie movie) {
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);

        return movieDTO;
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Genre>> getAllGenres(){
        return ResponseEntity.ok(movieService.getAllGenres());
    }
}