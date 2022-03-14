package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.dto.statistic.StatisticMovieDTO;
import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import a0120i1.codegym.cinema_management.model.movie.Movie;
import a0120i1.codegym.cinema_management.repository.IMovieRepository;
import a0120i1.codegym.cinema_management.repository.IShowTimeRepository;
import a0120i1.codegym.cinema_management.service.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ShowTimeService implements IShowTimeService {

    @Autowired
    private final IShowTimeRepository showTimeRepository;
    @Autowired
    private final IMovieRepository movieRepository;

    public ShowTimeService(IShowTimeRepository showTimeRepository, IMovieRepository movieRepository) {
        this.showTimeRepository = showTimeRepository;
        this.movieRepository = movieRepository;
    }


    @Override
    public Optional<ShowTime> getById(String id) {
        return this.showTimeRepository.findById(id);
    }

    @Override
    public ShowTime save(ShowTime entity) {
        return this.showTimeRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        this.showTimeRepository.deleteById(id);
    }

    @Override
    public List<ShowTime> listShowTimeByMovieID(String id) {
        return showTimeRepository.findShowTimeByMovie_Id(id);
    }

    @Override
    public List<ShowTime> getAll() {
        return showTimeRepository.findAll();
    }

    @Override
    public Optional<ShowTime> getById(String id) {
        return Optional.empty();
    }

    @Override
    public ShowTime save(ShowTime entity) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<StatisticMovieDTO> statisticTopHighestGrossingMovie() {
        List<Movie> movieList = this.movieRepository.findAll();
        List<StatisticMovieDTO> statisticMovieDTOList = new ArrayList<>();
        for (Movie movie : movieList) {
            Double price = this.showTimeRepository.sumPriceByMovieId(movie.getId());
            Integer quantity = this.showTimeRepository.sumTicketQuantityByMovieId(movie.getId());
            statisticMovieDTOList.add(new StatisticMovieDTO(movie.getName(), quantity, price));
        }
        Collections.sort(statisticMovieDTOList);
        return statisticMovieDTOList;
    }
}
