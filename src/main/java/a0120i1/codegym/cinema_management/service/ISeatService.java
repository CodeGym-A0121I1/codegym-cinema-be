package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.model.theater.Seat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISeatService {
    List<Seat> findAllByTheaterID(String theaterId);
    List<Seat> findAllSeatBookedInTheater( String theater);
    List<Seat> findAllSeatBookingId(String bookingId);
}
