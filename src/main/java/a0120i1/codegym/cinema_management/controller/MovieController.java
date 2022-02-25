package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.service.IMovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin
@RequestMapping("api/movie")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getMovieByDateSelected(@RequestParam("date") String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        List<Movie> moviesByDate = movieService.findMovieByOpeningDayBetweenAndEndDay2(localDate);
        return moviesByDate.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(moviesByDate, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<List<Movie>> findAll(){
        List<Movie> movieList = movieService.getAll();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable String id){
        Optional<Movie> movie = movieService.getById(id);
        if (!movie.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            movieService.deleteById(id);
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Movie> editUser(@RequestBody Movie movie) {
        Optional<Movie> currentMovie = movieService.getById(movie.getId());
        if (!currentMovie.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie newMovie = movieService.save(movie);
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> detailmovie(@PathVariable("id") String id) {
        Optional<Movie> movie = movieService.getById(id);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
