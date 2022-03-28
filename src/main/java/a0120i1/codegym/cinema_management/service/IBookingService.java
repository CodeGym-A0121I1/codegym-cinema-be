package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.dto.statistic.StatisticMemberDTO;
import a0120i1.codegym.cinema_management.model.booking.Booking;
import a0120i1.codegym.cinema_management.model.booking.Ticket;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookingService extends IService<Booking, String> {
    List<Booking> findBy(String search);

    List<Booking> listBookingByFalse();

    List<StatisticMemberDTO> statisticTopMemberByTotalPrice();

    Boolean sendMail(Booking booking);

}
