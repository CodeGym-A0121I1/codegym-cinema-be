package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.model.theater.Seat;
import a0120i1.codegym.cinema_management.repository.ISeatRepository;
import a0120i1.codegym.cinema_management.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService implements ISeatService {
    @Autowired
    ISeatRepository iSeatRepository;
    @Override
    public List<Seat> findAllByTheaterID(String theaterId) {
        return iSeatRepository.findAllByTheater_Id(theaterId);
    }

    @Override
    public List<Seat> findAllSeatBookedInTheater(String theater, String time,String movieId,String date) {
        return iSeatRepository.findAllSeatBookedInTheater(theater,time,movieId,date);
    }

    @Override
    public List<Seat> findAllSeatBookingId(String bookingId) {
        return iSeatRepository.findAllSeatBookingId(bookingId);
    }

    @Override
    public Optional<Seat> findIdByName(String name) {
        return iSeatRepository.findIdByName(name);
    }
}
