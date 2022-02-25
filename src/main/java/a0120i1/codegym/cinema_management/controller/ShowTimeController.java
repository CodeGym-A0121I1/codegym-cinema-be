package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.dto.statisticDTO.StatisticMemberDTO;
import a0120i1.codegym.cinema_management.dto.statisticDTO.StatisticMovieDTO;
import a0120i1.codegym.cinema_management.service.IBookingService;
import a0120i1.codegym.cinema_management.service.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistic")
@CrossOrigin
public class ShowTimeController {

    @Autowired
    private final IShowTimeService showTimeService;

    @Autowired
    private final IBookingService bookingService;

    public ShowTimeController(IShowTimeService showTimeService, IBookingService bookingService) {
        this.showTimeService = showTimeService;
        this.bookingService = bookingService;
    }

    @GetMapping("/movie")
    public ResponseEntity<List<StatisticMovieDTO>> showTopHighestGrossingMovie() {
        List<StatisticMovieDTO> statisticMovieDTOList = this.showTimeService.statisticTopHighestGrossingMovie();
        return new ResponseEntity<>(statisticMovieDTOList, HttpStatus.OK);
    }

    @GetMapping("/member-quantity")
    public ResponseEntity<List<StatisticMemberDTO>> showTopMemberByQuantity() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/member-price")
    public ResponseEntity<List<StatisticMemberDTO>> showTopMemberByTotalPrice() {
        List<StatisticMemberDTO> statisticMemberDTOList = this.bookingService.statisticTopMemberByTotalPrice();
        return new ResponseEntity<>(statisticMemberDTOList, HttpStatus.OK);
    }

    @GetMapping("/member-accumulator")
    public ResponseEntity<List<StatisticMemberDTO>> showTopMemberByAccumulator() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
