package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.dto.statistic.StatisticMovieDTO;
import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IShowTimeService extends IService<ShowTime, String> {
    List<ShowTime> listShowTimeByMovieID(String id);
    List<StatisticMovieDTO> statisticTopHighestGrossingMovie();
}
