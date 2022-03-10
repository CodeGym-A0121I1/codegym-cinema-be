package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.model.booking.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookingService extends IService<Booking, String> {
    List<Booking> findBy(String search);

    List<Booking> listBookingByFalse();
}
