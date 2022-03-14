package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.dto.movie.ShowTimeDTO;
import a0120i1.codegym.cinema_management.dto.movie.TheaterDTO;
import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import a0120i1.codegym.cinema_management.model.movie.*;
import a0120i1.codegym.cinema_management.model.theater.Theater;
import a0120i1.codegym.cinema_management.service.IMovieService;
import a0120i1.codegym.cinema_management.service.IShowTimeService;
import a0120i1.codegym.cinema_management.service.ITheaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/movie")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private ITheaterService theaterService;

    @Autowired
    private IShowTimeService showTimeService;


    @GetMapping
    public ResponseEntity<List<Movie>> getMovieByDateSelected(@RequestParam("date") String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        List<Movie> moviesByDate = movieService.findMovieByOpeningDayBetweenAndEndDay2(localDate);
        return moviesByDate.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(moviesByDate, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<List<Movie>> findAll() {
        List<Movie> movieList = movieService.getAll();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable String id) {
        Optional<Movie> movie = movieService.getById(id);
        if (!movie.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            movieService.deleteById(id);
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        }
    }

    @PutMapping("")
    public ResponseEntity<Movie> editMovie(@RequestBody Movie movie) {
        Optional<Movie> currentMovie = movieService.getById(movie.getId());
        if (!currentMovie.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie newMovie = movieService.save(movie);
        if (newMovie == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> detailmovie(@PathVariable("id") String id) {
        Optional<Movie> movie = movieService.getById(id);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @GetMapping("/genre")
    public ResponseEntity<List<Genre>> getAllGenre() {
        List<Genre> genreList = movieService.getAllGenre();
        return new ResponseEntity<>(genreList, HttpStatus.OK);
    }

    @GetMapping("/actor")
    public ResponseEntity<List<Actor>> getAllActor() {
        List<Actor> actorList = movieService.getAllActor();
        return new ResponseEntity<>(actorList, HttpStatus.OK);
    }

    @GetMapping("/director")
    public ResponseEntity<List<Director>> getAllDirector() {
        List<Director> directorList = movieService.getAllDirector();
        return new ResponseEntity<>(directorList, HttpStatus.OK);
    }

    @GetMapping("/producer")
    public ResponseEntity<List<Producer>> getAllProducer() {
        List<Producer> producerList = movieService.getAllProducer();
        return new ResponseEntity<>(producerList, HttpStatus.OK);
    }

    @GetMapping("/theater")
    public ResponseEntity<List<TheaterDTO>> getAllTheater() {
        List<Theater> theaterList = theaterService.getAll();

        List<TheaterDTO> theaterDTOList = new ArrayList<>();

        for (Theater theater : theaterList) {
            TheaterDTO theaterDTO = new TheaterDTO();

            theaterDTO.setId(theater.getId());
            theaterDTO.setName(theater.getName());

            theaterDTOList.add(theaterDTO);
        }

        return new ResponseEntity<>(theaterDTOList, HttpStatus.OK);
    }

    @GetMapping("/all/showtime")
    public ResponseEntity<List<ShowTimeDTO>> getAllShowTime() {
        List<ShowTime> showTimeList = showTimeService.getAll();

        List<ShowTimeDTO> showTimeDTOList = new ArrayList<>();

        for (ShowTime showTime : showTimeList) {
            ShowTimeDTO showTimeDTO = new ShowTimeDTO();

            showTimeDTO.setId(showTime.getId());
            showTimeDTO.setStartTime(showTime.getStartTime());

            showTimeDTOList.add(showTimeDTO);
        }

        return new ResponseEntity<>(showTimeDTOList, HttpStatus.OK);
    }

}
