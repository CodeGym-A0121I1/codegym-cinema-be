package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.model.booking.ShowTime;
import a0120i1.codegym.cinema_management.model.booking.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IShowTimeService {
    List<ShowTime> listShowTimeByMovieID(String id);
    List<ShowTime> findShowTimeByMovie_IdAndTheater_Id(String idMovie, String idTheater);
}
