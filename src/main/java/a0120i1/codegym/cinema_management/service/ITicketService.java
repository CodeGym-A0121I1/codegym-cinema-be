package a0120i1.codegym.cinema_management.service;

import a0120i1.codegym.cinema_management.model.booking.Ticket;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITicketService extends IService<Ticket, String> {
    List<Ticket> ticketByBooking(String id);

    List<Ticket> ticketByBookingIdangSeartName(String idbooking);

    Ticket updatepaidticket(String idbooking);

}
