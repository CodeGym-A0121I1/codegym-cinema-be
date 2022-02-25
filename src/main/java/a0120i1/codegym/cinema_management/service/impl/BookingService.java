package a0120i1.codegym.cinema_management.service.impl;

import a0120i1.codegym.cinema_management.dto.statisticDTO.StatisticMemberDTO;
import a0120i1.codegym.cinema_management.model.booking.Booking;
import a0120i1.codegym.cinema_management.model.user.User;
import a0120i1.codegym.cinema_management.repository.IBookingRepository;
import a0120i1.codegym.cinema_management.repository.IShowTimeRepository;
import a0120i1.codegym.cinema_management.repository.IUserRepository;
import a0120i1.codegym.cinema_management.service.IBookingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {

    private final IBookingRepository bookingRepository;
    private final IShowTimeRepository showTimeRepository;
    private final IUserRepository userRepository;

    public BookingService(IBookingRepository bookingService, IShowTimeRepository showTimeRepository, IUserRepository userService) {
        this.bookingRepository = bookingService;
        this.showTimeRepository = showTimeRepository;
        this.userRepository = userService;
    }

    @Override
    public List<StatisticMemberDTO> statisticTopMemberByTotalPrice() {
        List<User> userList = this.userRepository.findAll();
        List<StatisticMemberDTO> statisticMemberDTOList = new ArrayList<>();
        for (User user : userList) {
            Double totalPrice = this.bookingRepository.sumPriceByUserId(user.getId());
            System.out.printf("Tổng tiền của thành viên " + user.getFullName() + " là %6.2f", totalPrice);
//            String quantit
            statisticMemberDTOList.add(new StatisticMemberDTO(user.getId(), user.getFullName(), totalPrice));
        }
        return statisticMemberDTOList;
    }

    @Override
    public List<StatisticMemberDTO> statisticTopMemberByQuantity() {
        return null;
    }

    @Override
    public List<StatisticMemberDTO> statisticTopMemberByAccumulator() {
        return null;
    }

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
}
