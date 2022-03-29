package a0120i1.codegym.cinema_management.controller;

import a0120i1.codegym.cinema_management.dto.statistic.StatisticMemberDTO;
import a0120i1.codegym.cinema_management.dto.statistic.StatisticMovieDTO;
import a0120i1.codegym.cinema_management.service.ITicketService;
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
public class StatisticController {

    @Autowired
    private final ITicketService ticketService;

    public StatisticController(ITicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/movie")
    public ResponseEntity<List<StatisticMovieDTO>> showTopHighestGrossingMovie() {
        List<StatisticMovieDTO> statisticMovieDTOList = this.ticketService.statisticTopHighestGrossingMovie();
        return new ResponseEntity<>(statisticMovieDTOList, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<StatisticMemberDTO>> showTopMemberByQuantity() {
        List<StatisticMemberDTO> statisticMemberDTOList = this.ticketService.statisticTopMemberByTotalPrice();
        return new ResponseEntity<>(statisticMemberDTOList, HttpStatus.OK);
    }
}