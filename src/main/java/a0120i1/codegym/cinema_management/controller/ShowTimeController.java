package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.dto.statisticMovieDTO.StatisticMovieDTO;
import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import a0120i1.codegym.cinema_management.service.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/statistic")
@CrossOrigin
public class ShowTimeController {

    @Autowired
    private final IShowTimeService showTimeService;

    public ShowTimeController(IShowTimeService showTimeService) {
        this.showTimeService = showTimeService;
    }

    @GetMapping("/movie")
    public ResponseEntity<List<StatisticMovieDTO>> showTopHighestGrossingMovie() {
        List<StatisticMovieDTO> statisticMovieDTOList = this.showTimeService.statisticTopHighestGrossingMovie();
        return new ResponseEntity<>(statisticMovieDTOList, HttpStatus.OK);
    }

}
