package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("api/movie")
public class MovieController {
    @Autowired
    private IMovieService movieService;

    @PutMapping("/edit")
    public ResponseEntity<Movie> editUser(@RequestBody Movie movie) {
        Optional<Movie> currentMovie = movieService.getById(movie.getId());
        if (!currentMovie.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.OK);
    }
}
