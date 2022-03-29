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

    @Query(value = "select sum(price) as totalMoney " +
            "from ticket group by booking_id " +
            "having booking_id = :id", nativeQuery = true)
    float bookingToTalMoney(@Param("id") String id);

    @Query(value = "select ticket.* from ticket join seat s on s.id = ticket.seat_id " +
            " join booking b on b.id = ticket.booking_id where ticket.booking_id= 'B2'", nativeQuery = true)
    List<Ticket> ticketByBookingIdangSeartName(@Param("idbooking") String idbooking);

    @Query(value = "SELECT SUM(price) FROM ticket t " +
            "WHERE t.booking_id IN (SELECT id FROM booking b " +
            "WHERE b.show_time_id IN (SELECT id FROM show_time st WHERE st.movie_id = :movieId));", nativeQuery = true)
    Double sumPriceByMovieId(@Param("movieId") String movieId);

    @Query(value = "SELECT COUNT(id) FROM ticket t " +
            "WHERE t.booking_id IN (SELECT id FROM booking b " +
            "WHERE b.show_time_id IN (SELECT id FROM show_time st WHERE st.movie_id = :movieId));", nativeQuery = true)
    Integer sumTicketQuantityByMovieId(@Param("movieId") String movieId);

    @Query(value = "SELECT SUM(price) FROM ticket t " +
            "WHERE t.booking_id IN (SELECT id FROM booking b " +
            "WHERE b.user_id = :userId)", nativeQuery = true)
    Double sumPriceByUserId(@Param("userId") String userId);

    @Query(value = "SELECT COUNT(id) FROM ticket t " +
            "WHERE t.booking_id IN (SELECT id FROM booking b " +
            "WHERE b.user_id = :userId)", nativeQuery = true)
    Integer sumTicketQuantityByUserId(@Param("userId") String userId);
}
