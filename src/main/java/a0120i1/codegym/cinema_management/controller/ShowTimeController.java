package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import a0120i1.codegym.cinema_management.model.theater.Seat;
import a0120i1.codegym.cinema_management.service.ISeatService;
import a0120i1.codegym.cinema_management.service.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/showTime")
public class ShowTimeController {

    @Autowired
    private IShowTimeService showTimeService;

    @Autowired
    private ISeatService seatService;

    //get list ShowTime by Movie Id
    @GetMapping()
    public ResponseEntity<List<ShowTime>> getShowTimeByMovieId(@RequestParam("MovieId") String movieId) {
        List<ShowTime> showTimes = showTimeService.listShowTimeByMovieID(movieId);
        return showTimes.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(showTimes, HttpStatus.OK);
    }
    //get list ShowTime by Movie Id and date
    @GetMapping("/movie")
    public ResponseEntity<List<ShowTime>> getShowTimeByMovieIdAndDateStart(@RequestParam("movieId") String movieId,@RequestParam("dateStart") String date) {
        List<ShowTime> showTimes = showTimeService.findShowTimeByStartDateAndMovieId(date,movieId);
        return showTimes.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(showTimes, HttpStatus.OK);
    }


    //get all seat in theater by theaterId
    @GetMapping("/seats")
    public ResponseEntity<List<Seat>> getAllSeatByTheaterId(@RequestParam("theaterId") String theaterId) {
        List<Seat> seatList = seatService.findAllByTheaterID(theaterId);
        return new ResponseEntity<>(seatList, HttpStatus.OK);
    }

    //get all seat in theater booked
    @GetMapping("/booked")
    public ResponseEntity<List<Seat>> getAllSeatBookedByTheaterId(@RequestParam("theaterId") String theaterId,@RequestParam("timeShowTime") String timeShowTime,@RequestParam("movieId") String movieId,@RequestParam("date") String date) {
        List<Seat> seatList = seatService.findAllSeatBookedInTheater(theaterId,timeShowTime,movieId,date);
        return new ResponseEntity<>(seatList, HttpStatus.OK);
    }

    //get all seat thei idboooking
    @GetMapping("/seat/{bookingId}")
    public ResponseEntity<List<Seat>> findAllSeatBookingId(@PathVariable("bookingId") String bookingId) {
        List<Seat> seatList = seatService.findAllSeatBookingId(bookingId);
        return new ResponseEntity<>(seatList, HttpStatus.OK);
    }

    //    get list ShowTime by Movie Id and theaterId
    @GetMapping("/showmovietheater")
    public ResponseEntity<List<ShowTime>> getShowTimeByMovieIdandTheaterId(@RequestParam("MovieId") String MovieId, @RequestParam("TheaterId") String TheaterId) {
        List<ShowTime> showTimes = showTimeService.findShowTimeByMovie_IdAndTheater_Id(MovieId, TheaterId);
        return showTimes.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(showTimes, HttpStatus.OK);
    }
}
