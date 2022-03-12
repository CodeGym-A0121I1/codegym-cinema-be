package a0120i1.codegym.cinema_management.repository;

import a0120i1.codegym.cinema_management.model.theater.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findAllByTheater_Id(String theaterId);

    @Query(value = "SELECT seat.* FROM ticket join seat on seat.id= ticket.seat_id where seat.theater_id= :theaterId", nativeQuery = true)
    List<Seat> findAllSeatBookedInTheater(@Param("theaterId") String theater);

    @Query(value = "select  seat.* from seat  join ticket t on seat.id = t.seat_id\n" +
            " join booking b on t.booking_id = b.id where booking_id =:bookingId", nativeQuery = true)
    List<Seat> findAllSeatBookingId(@Param("bookingId") String bookingId);

    Optional<Seat> findIdByName(String name);
}
