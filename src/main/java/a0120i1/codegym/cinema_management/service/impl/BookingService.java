package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.dto.statistic.StatisticMemberDTO;
import a0120i1.codegym.cinema_management.model.booking.Booking;
import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.repository.IBookingRepository;
import a0120i1.codegym.cinema_management.repository.IUserRepository;
import a0120i1.codegym.cinema_management.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private IBookingRepository bookingRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<Booking> getAll() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getById(String id) {
        return this.bookingRepository.findById(id);
    }

    @Override
    public Booking save(Booking entity) {
        return this.bookingRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        this.bookingRepository.deleteById(id);
    }

    @Override
    public List<StatisticMemberDTO> statisticTopMemberByTotalPrice() {
        List<User> userList = this.userRepository.findAll();
        List<StatisticMemberDTO> statisticMemberDTOList = new ArrayList<>();
        for (User user : userList) {
            Double totalPrice = this.bookingRepository.sumPriceByUserId(user.getId());
            Integer quantity = this.bookingRepository.countQuantity(user.getId());
            statisticMemberDTOList.add(new StatisticMemberDTO(user.getId(), user.getFullName(), quantity, totalPrice));
        }
        Collections.sort(statisticMemberDTOList);
        return statisticMemberDTOList;
    }

    @Override
    public List<Booking> findBy(String search) {
        return bookingRepository.findBy(search);
    }

    @Override
    public List<Booking> listBookingByFalse() {
        return bookingRepository.listBookingByFalse();
    }

    @Override
    public List<Booking> ByBooking(String id) {
        return bookingRepository.ByBooking(id);
    }


}
