package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.dto.statistic.StatisticMemberDTO;
import a0120i1.codegym.cinema_management.dto.statistic.StatisticMovieDTO;
import a0120i1.codegym.cinema_management.model.booking.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITicketService extends IService<Ticket, String> {
    List<Ticket> ticketByBooking(String id);

    List<Ticket> findTicketsByBookingId(String idbooking);

    float bookingToTalMoney(String id);

    List<StatisticMovieDTO> statisticTopHighestGrossingMovie();

    List<StatisticMemberDTO> statisticTopMemberByTotalPrice();
}
