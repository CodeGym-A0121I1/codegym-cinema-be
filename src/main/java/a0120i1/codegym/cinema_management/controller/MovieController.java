package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.service.IMovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }


//     chi tiết phim
    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> detailmovie(@PathVariable("id") String id) {
        System.out.println("test xem id có vao trong nay ko nao");
        Movie movie = this.movieService.finById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}
