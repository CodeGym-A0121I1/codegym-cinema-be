package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.booking.Ticket;
import a0120i1.codegym.cinema_management.model.theater.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, String> {
    @Query("select t from Ticket as t where t.booking.id = :id")
    List<Ticket> ticketByBooking(@Param("id") String id);

    @Query(value = "select ticket.* from ticket join seat s on s.id = ticket.seat_id " +
            " join booking b on b.id = ticket.booking_id where ticket.booking_id= 'B2'", nativeQuery = true)
    List<Ticket> ticketByBookingIdangSeartName(@Param("idbooking") String idbooking);

}
