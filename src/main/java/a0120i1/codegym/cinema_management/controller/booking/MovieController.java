package a0120i1.codegym.cinema_management.controller.booking;

import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.service.IMovieService;
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
@RequestMapping("/api/movie")
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
}
