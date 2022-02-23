package a0120i1.codegym.cinema_management.controller.booking;

import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.model.theater.Seat;
import a0120i1.codegym.cinema_management.repository.IShowTimeRepository;
import a0120i1.codegym.cinema_management.service.ISeatService;
import a0120i1.codegym.cinema_management.service.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ShowTimeController {
    @Autowired
    IShowTimeService showTimeService;
    @Autowired
    ISeatService seatService;
    //get list ShowTime by Movie Id
    @GetMapping("/showTime")
    public ResponseEntity<List<ShowTime>> getShowTimeByMovieId(@RequestParam("MovieId") String movieId){
       List<ShowTime> showTimes= showTimeService.listShowTimeByMovieID(movieId);
        return showTimes.isEmpty()? new ResponseEntity<>(HttpStatus.NOT_FOUND):new ResponseEntity<>(showTimes,HttpStatus.OK);
    }
    //get all seat in theater by theaterId
    @GetMapping("/seats")
    public  ResponseEntity<List<Seat>> getAllSeatByTheaterId(@RequestParam("theaterId")String theaterId){
        List<Seat> seatList = seatService.findAllByTheaterID(theaterId);
        return new ResponseEntity<>(seatList,HttpStatus.OK);
    }
    //get all seat in theater booked
    @GetMapping("/seats/booked")
    public  ResponseEntity<List<Seat>> getAllSeatBookedByTheaterId(@RequestParam("theaterId")String theaterId){
        List<Seat> seatList = seatService.findAllSeatBookedInTheater(theaterId);
        return new ResponseEntity<>(seatList,HttpStatus.OK);
    }

}
