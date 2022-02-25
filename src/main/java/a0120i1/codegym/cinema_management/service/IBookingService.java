package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.dto.statisticDTO.StatisticMemberDTO;
import a0120i1.codegym.cinema_management.model.booking.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookingService extends IService<Booking, String> {
    List<StatisticMemberDTO> statisticTopMemberByTotalPrice();
    List<StatisticMemberDTO> statisticTopMemberByQuantity();
    List<StatisticMemberDTO> statisticTopMemberByAccumulator();
}
