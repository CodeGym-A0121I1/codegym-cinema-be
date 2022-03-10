package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.model.booking.Booking;
import a0120i1.codegym.cinema_management.repository.IBookingRepository;
import a0120i1.codegym.cinema_management.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {
    @Autowired
    private IBookingRepository bookingRepository;

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getById(String id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Booking save(Booking entity) {
        return bookingRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public List<Booking> findBy(String search) {
        return bookingRepository.findBy(search);
    }

    @Override
    public   List<Booking>  updatepaidbooking(String idbooking) {
        return bookingRepository.updatepaidbooking(idbooking);
    }

}
